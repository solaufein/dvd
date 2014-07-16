package pl.radek.dvd.dto.clients;

import java.util.Date;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 17:22
 */
public class ClientDetails {
    private int id;               // renting registry id!
    private String title;         // movie title
    private String serialNumber;      // movie copy serial number
    private Date rentDate;
    private Date returnDate;

    public ClientDetails() {
    }

    public ClientDetails(String title, String serialNumber, Date rentDate, Date returnDate) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public ClientDetails(int id, String title, String serialNumber, Date rentDate, Date returnDate) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
