package pl.radek.dvd.service.renting;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.rr.NewRentDto;
import pl.radek.dvd.logic.EmployeeDAO;
import pl.radek.dvd.logic.clients.ClientsDAO;
import pl.radek.dvd.logic.movieCopy.MovieCopyDAO;
import pl.radek.dvd.logic.receipts.ReceiptDAO;
import pl.radek.dvd.logic.renting.RentingDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.model.Receipt;
import pl.radek.dvd.service.clients.ClientsService;
import pl.radek.dvd.utils.UtilJavaMethods;

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
    public int addRentingRegistry(NewRentDto rentDto) {
        MovieCopy movieCopy = movieCopyDAO.getMovieCopy(rentDto.getMovieCopyId());
        Client client = clientsDAO.getClient(rentDto.getClientId());
        Employee employee = employeeDAO.getEmployee(rentDto.getEmployeeName());
        Date returnDate = rentDto.getReturnDate();

        String billNumber = UtilJavaMethods.randomString(UtilJavaMethods.CHARSET_AZ_09, 10);
        Receipt receipt = new Receipt();
        receipt.setBillNumber(billNumber);
        receiptDAO.addReceipt(receipt);

        int rentingRegistryId = rentingDAO.saveRentingRegistry(employee, client, movieCopy, returnDate, receipt);

        //todo: set movie copy availability to 0
        //todo: add column to rentingRegistry: expected_return_date OR in movie copies view, REturn count from receipt Pay Date!

        return rentingRegistryId;
    }
}
