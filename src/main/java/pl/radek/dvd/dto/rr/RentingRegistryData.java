package pl.radek.dvd.dto.rr;

import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.model.Receipt;

import java.util.Date;

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
