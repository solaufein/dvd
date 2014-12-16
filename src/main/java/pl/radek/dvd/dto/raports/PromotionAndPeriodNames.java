package pl.radek.dvd.dto.raports;

import java.util.Set;

public class PromotionAndPeriodNames {
    Set<String> promotionNames;
    Set<Number> periodNames;

    public PromotionAndPeriodNames() {
    }

    public PromotionAndPeriodNames(Set<String> promotionNames, Set<Number> periodNames) {
        this.promotionNames = promotionNames;
        this.periodNames = periodNames;
    }

    public Set<String> getPromotionNames() {
        return promotionNames;
    }

    public Set<Number> getPeriodNames() {
        return periodNames;
    }
}
