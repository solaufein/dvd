package pl.radek.dvd.dto.raports;

import java.util.Set;

public class PromotionAndPeriodNames {
    Set<String> promotionNames;
    Set<String> periodNames;

    public PromotionAndPeriodNames() {
    }

    public PromotionAndPeriodNames(Set<String> promotionNames, Set<String> periodNames) {
        this.promotionNames = promotionNames;
        this.periodNames = periodNames;
    }

    public Set<String> getPromotionNames() {
        return promotionNames;
    }

    public Set<String> getPeriodNames() {
        return periodNames;
    }
}
