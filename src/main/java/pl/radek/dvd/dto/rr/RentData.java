package pl.radek.dvd.dto.rr;

import java.math.BigDecimal;
import java.util.Date;

public class RentData {
    private String title;
    private String serialNumber;
    private String promotion;
    private BigDecimal price;   // promotion value
    private short promotionDaysNumber;
    private String expectedReturnDate;

    public RentData() {
    }

    public RentData(String title, String serialNumber, String promotion, BigDecimal price, short promotionDaysNumber) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.promotion = promotion;
        this.price = price;
        this.promotionDaysNumber = promotionDaysNumber;
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

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public short getPromotionDaysNumber() {
        return promotionDaysNumber;
    }

    public void setPromotionDaysNumber(short promotionDaysNumber) {
        this.promotionDaysNumber = promotionDaysNumber;
    }

    public String getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(String expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }
}
