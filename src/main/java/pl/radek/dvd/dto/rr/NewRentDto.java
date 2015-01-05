package pl.radek.dvd.dto.rr;

import java.math.BigDecimal;
import java.util.Date;

public class NewRentDto {
    private int rentingId;
    private int clientId;
    private int movieCopyId;
    private int receiptId;
    private String employeeName;
    private Date returnDate;
    private Date rentDate;
    private String comment;
    private short promotionDaysNumber;
    private BigDecimal price;

    public NewRentDto() {
    }

    public NewRentDto(short promotionDaysNumber, int clientId, int movieCopyId, String employeeName, BigDecimal price) {
        this.promotionDaysNumber = promotionDaysNumber;
        this.clientId = clientId;
        this.movieCopyId = movieCopyId;
        this.employeeName = employeeName;
        this.price = price;
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

    public short getPromotionDaysNumber() {
        return promotionDaysNumber;
    }

    public void setPromotionDaysNumber(short promotionDaysNumber) {
        this.promotionDaysNumber = promotionDaysNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
