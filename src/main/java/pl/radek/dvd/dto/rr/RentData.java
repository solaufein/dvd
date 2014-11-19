package pl.radek.dvd.dto.rr;

/**
 * Created by Sola on 2014-11-19.
 */
public class RentData {
    private String title;
    private String serialNumber;
    private String promotion;

    public RentData() {
    }

    public RentData(String title, String serialNumber, String promotion) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.promotion = promotion;
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
}
