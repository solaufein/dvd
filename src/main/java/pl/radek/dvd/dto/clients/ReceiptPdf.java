package pl.radek.dvd.dto.clients;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: Sola
 * Date: 2014-06-24
 * Time: 17:07
 */

/**
 *  This class is DTO-class prepared for pdf generated view
 */
public class ReceiptPdf {
    private String title;         // movie title
    private String serialNumber;      // movie copy serial number
    private Date rentDate;
    private Date returnDate;
    private BigDecimal price;
    private Date payDate;
    private String billNumber;

    public ReceiptPdf() {
    }

    public ReceiptPdf(String title, String serialNumber, Date rentDate, Date returnDate, BigDecimal price, Date payDate, String billNumber) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.payDate = payDate;
        this.billNumber = billNumber;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }
}
