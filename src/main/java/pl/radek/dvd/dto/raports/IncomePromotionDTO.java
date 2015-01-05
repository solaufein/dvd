package pl.radek.dvd.dto.raports;

import java.math.BigDecimal;
import java.util.Date;

public class IncomePromotionDTO {
    private Date period;  // day, month, year
    private String promotion;
    private Number count;

    public IncomePromotionDTO() {
    }

    public IncomePromotionDTO(Date period, String promotion, Number count) {
        this.period = period;
        this.promotion = promotion;
        this.count = count;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
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
