package pl.radek.dvd.dto.rr;

import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.model.Receipt;

import java.util.Date;

/**
 * User: Sola
 * Date: 2014-10-28
 * Time: 16:50
 */
public class RentingRegistryData {
    private int id;
    private Date rentDate;
    private Date returnDate;
    private String comment;
    private Client client;
    private Employee employee;
    private Receipt receipt;
    private MovieCopy movieCopy;

    public RentingRegistryData() {
    }
}
