package pl.radek.dvd.dto.raports;

import java.math.BigDecimal;

/**
 * Created by Sola on 2014-12-15.
 */
public class IncomePromotionDTO {
    private Number period;  // day, month, year         //todo: zmienic na Date. bo z niej latwiej pozniej wyciagac informacje w javie
    private String promotion;
    private Number count;

    public IncomePromotionDTO() {
    }

    public IncomePromotionDTO(Number period, String promotion, Number count) {
        this.period = period;
        this.promotion = promotion;
        this.count = count;
    }

    public Number getPeriod() {
        return period;
    }

    public void setPeriod(Number period) {
        this.period = period;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Number getCount() {
        return count;
    }

    public void setCount(Number count) {
        this.count = count;
    }
}
