package pl.radek.dvd.service.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.dto.rr.ReturnCommentDto;
import pl.radek.dvd.exceptions.movie.MovieCopyNotAvailableException;
import pl.radek.dvd.logic.EmployeeDAO;
import pl.radek.dvd.logic.clients.ClientsDAO;
import pl.radek.dvd.logic.movieCopy.MovieCopyDAO;
import pl.radek.dvd.logic.receipts.ReceiptDAO;
import pl.radek.dvd.logic.renting.RentingDAO;
import pl.radek.dvd.model.*;
import pl.radek.dvd.service.clients.ClientsService;
import pl.radek.dvd.utils.UtilJavaMethods;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Transactional
public class RentingServiceImpl implements RentingService {
    private static Logger logger = Logger.getLogger(RentingServiceImpl.class);

    @Autowired
    MovieCopyDAO movieCopyDAO;
    @Autowired
    ClientsDAO clientsDAO;
    @Autowired
    RentingDAO rentingDAO;
    @Autowired
    EmployeeDAO employeeDAO;
    @Autowired
    ReceiptDAO receiptDAO;

    @Override
    public int addRentingRegistry(NewRentDto rentDto) throws MovieCopyNotAvailableException {
        MovieCopy movieCopy = movieCopyDAO.getMovieCopy(rentDto.getMovieCopyId());

        //check if movie copy is available, protection against double save the same movie copy
        if (movieCopy.getAvailability() == 0) {
            throw new MovieCopyNotAvailableException("This movie copy is not available!");
        }

        Client client = clientsDAO.getClient(rentDto.getClientId());
        Employee employee = employeeDAO.getEmployee(rentDto.getEmployeeName());
        Date returnDate = rentDto.getReturnDate();

        // add new receipt
        String billNumber = UtilJavaMethods.randomString(UtilJavaMethods.CHARSET_AZ_09, 10);
        Receipt receipt = new Receipt();
        receipt.setBillNumber(billNumber);
        receiptDAO.addReceipt(receipt);

        // save new renting registry
        int rentingRegistryId = rentingDAO.saveRentingRegistry(employee, client, movieCopy, returnDate, receipt);

        // update availability for movie copy = 0
        movieCopy.setAvailability((short) 0);
        movieCopyDAO.updateMovieCopy(movieCopy);

        return rentingRegistryId;
    }

    @Override
    public void updateRentingRegistry(ReturnCommentDto returnDto) {
        logger.info("UpdateRentingRegistry Service method entered");

        // get renting registry
        RentingRegistry rentingRegistry = rentingDAO.getRentingRegistry(returnDto.getRegistryId());
        Receipt receipt = rentingRegistry.getReceipt();     // get receipt from rr

        // update renting registry - set comment
        rentingRegistry.setComment(returnDto.getComment());
        rentingDAO.updateRentingRegistry(rentingRegistry);

        // update movie copy availability to 1
        MovieCopy movieCopy = movieCopyDAO.getMovieCopy(returnDto.getMovieCopyId());
        movieCopy.setAvailability((short) 1);
        movieCopyDAO.updateMovieCopy(movieCopy);

        // update receipt - pay date and price
        receipt.setPayDate(new Date());
        receipt.setPrice(new BigDecimal("100.20"));     // in future we can implement some logic to automatic count price, or manually set price via view
        receiptDAO.updateReceipt(receipt);

        logger.info("UpdateRentingRegistry Service method leaved");
    }
}
