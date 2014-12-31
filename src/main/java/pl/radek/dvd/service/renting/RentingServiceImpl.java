package pl.radek.dvd.service.renting;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Instant;
import org.joda.time.Interval;
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
import java.math.RoundingMode;
import java.util.Calendar;
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

        short promotionDaysNumber = rentDto.getPromotionDaysNumber();
        Date returnDate = UtilJavaMethods.currentDatePlusNoOfDays(promotionDaysNumber);

        //    Date returnDate = rentDto.getReturnDate();

        // add new receipt
        String billNumber = UtilJavaMethods.randomString(UtilJavaMethods.CHARSET_AZ_09, 10);
        Receipt receipt = new Receipt();
        receipt.setBillNumber(billNumber);
        receipt.setPrice(rentDto.getPrice());
        receiptDAO.addReceipt(receipt);

        // save new renting registry
        int rentingRegistryId = rentingDAO.saveRentingRegistry(employee, client, movieCopy, returnDate, receipt);

        // update availability for movie copy = 0
        movieCopy.setAvailability((short) 0);
        movieCopyDAO.updateMovieCopy(movieCopy);

        return rentingRegistryId;
    }

    @Override
    public boolean updateRentingRegistry(ReturnCommentDto returnDto) {
        logger.info("UpdateRentingRegistry Service method entered");
        boolean isLate = false;

        // get renting registry
        RentingRegistry rentingRegistry = rentingDAO.getRentingRegistry(returnDto.getRegistryId());
        Receipt receipt = rentingRegistry.getReceipt();     // get receipt from rr
        BigDecimal price = receipt.getPrice();  // get price from receipt
        Date returnDate = rentingRegistry.getReturnDate();
        Date currentDate = new Date();

        // update renting registry - set comment
        rentingRegistry.setComment(returnDto.getComment());
        rentingDAO.updateRentingRegistry(rentingRegistry);

        // update movie copy availability to 1
        MovieCopy movieCopy = movieCopyDAO.getMovieCopy(returnDto.getMovieCopyId());
        movieCopy.setAvailability((short) 1);
        movieCopyDAO.updateMovieCopy(movieCopy);

        // update receipt - pay date and price
        receipt.setPayDate(currentDate);
        // logic to automatic count price
        int days = Days.daysBetween(new DateTime(returnDate), new DateTime(currentDate)).getDays();
        //    int days = Days.daysBetween(new DateTime(currentDate), new DateTime(returnDate)).getDays();   // for testing purposes
        if (days > 0) {
            BigDecimal totalCost = getTotalPrice(price, days);
            logger.info("totalCost = " + totalCost);
            logger.info("days = " + days);

            receipt.setPrice(totalCost);
            isLate = true;
        }

        receiptDAO.updateReceipt(receipt);

        logger.info("UpdateRentingRegistry Service method leaved");
        return isLate;
    }

    private BigDecimal getTotalPrice(BigDecimal price, int days) {
        double discountPercent = 0.02;
        BigDecimal totalCost = BigDecimal.ZERO;
        BigDecimal decimalDiscountPercent = new BigDecimal(Double.toString(discountPercent));   // 2%
        BigDecimal discountAmount = price.multiply(decimalDiscountPercent);     // 15zl * 2%
        discountAmount = discountAmount.setScale(2, RoundingMode.HALF_UP);
        BigDecimal debt = discountAmount.multiply(new BigDecimal(days));  //  debt = 5days * amount of discount
        totalCost = debt.add(price);        // sum price + debt
        return totalCost;
    }
}
