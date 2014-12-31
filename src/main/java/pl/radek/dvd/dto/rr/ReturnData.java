package pl.radek.dvd.dto.rr;

import pl.radek.dvd.utils.UtilJavaMethods;

import java.util.Date;

public class ReturnData {
    private int clientId;
    private int movieCopyId;
    private int rentingId;
    private String clientFirstName;
    private String clientLastName;
    private String title;
    private String serialNumber;
    private String promotionName;
    private String expectedReturnDate;

    public ReturnData(String clientFirstName, String clientLastName, String title, String serialNumber, String promotionName, Date expectedReturnDate) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.title = title;
        this.serialNumber = serialNumber;
        this.promotionName = promotionName;
        this.expectedReturnDate = UtilJavaMethods.formatDate("yyyy-MM-dd HH:mm:ss", expectedReturnDate);
    }

    public ReturnData(int clientId, int movieCopyId, int rentingId, String clientFirstName, String clientLastName, String title, String serialNumber, String promotionName) {
        this.clientId = clientId;
        this.movieCopyId = movieCopyId;
        this.rentingId = rentingId;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.title = title;
        this.serialNumber = serialNumber;
        this.promotionName = promotionName;
    }

    public ReturnData() {

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

    public int getRentingId() {
        return rentingId;
    }

    public void setRentingId(int rentingId) {
        this.rentingId = rentingId;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
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

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
