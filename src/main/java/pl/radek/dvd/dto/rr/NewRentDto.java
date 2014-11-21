package pl.radek.dvd.dto.rr;

import java.util.Date;

/**
 * Created by Sola on 2014-11-21.
 */
public class NewRentDto {
    private int rentingId;
    private int clientId;
    private int movieCopyId;
    private int receiptId;
    private String employeeName;
    private Date returnDate;
    private Date rentDate;
    private String comment;

    public NewRentDto() {
    }

    public NewRentDto(Date returnDate, int clientId, int movieCopyId, String employeeName) {
        this.returnDate = returnDate;
        this.clientId = clientId;
        this.movieCopyId = movieCopyId;
        this.employeeName = employeeName;
    }

    public NewRentDto(int rentingId, int clientId, int movieCopyId, int receiptId, String employeeName, Date returnDate, Date rentDate, String comment) {
        this.rentingId = rentingId;
        this.clientId = clientId;
        this.movieCopyId = movieCopyId;
        this.receiptId = receiptId;
        this.employeeName = employeeName;
        this.returnDate = returnDate;
        this.rentDate = rentDate;
        this.comment = comment;
    }

    public int getRentingId() {
        return rentingId;
    }

    public void setRentingId(int rentingId) {
        this.rentingId = rentingId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMovieCopyId() {
        return movieCopyId;
    }

    public void setMovieCopyId(int movieCopyId) {
        this.movieCopyId = movieCopyId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
