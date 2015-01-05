package pl.radek.dvd.dto.raports;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieNotReturnedDto {
    private static final SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Integer id;
    private String firstName;
    private String lastName;
    private String title; // movie title
    private String rentDate;
    private String returnDate;
    private int daysLate;
    private String phoneNumber;

    public MovieNotReturnedDto() {
    }

    public MovieNotReturnedDto(Integer id, String firstName, String lastName, String title, Date rentDate, Date returnDate, int daysLate, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.rentDate = sdfDate.format(rentDate);
        this.returnDate = sdfDate.format(returnDate);

        this.daysLate = daysLate < 0 ? 0 : daysLate;

        //    this.daysLate = daysLate;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = sdfDate.format(rentDate);
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = sdfDate.format(returnDate);
    }

    public int getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(int daysLate) {
        this.daysLate = daysLate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
