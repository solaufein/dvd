package pl.radek.dvd.logic.renting;

import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.model.Receipt;

import java.util.Date;

/**
 * Created by Sola on 2014-11-19.
 */
public interface RentingDAO {
    int saveRentingRegistry(Employee employee, Client client, MovieCopy movieCopy, Date returnDate, Receipt receipt);
}
