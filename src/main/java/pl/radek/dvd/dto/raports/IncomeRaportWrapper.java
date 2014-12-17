package pl.radek.dvd.dto.raports;

import java.util.List;

/**
 * Created by Sola on 2014-12-16.
 */
public class IncomeRaportWrapper implements PaginatedRaportWrapper<AmountPerX> {
    private PromotionAndPeriodNames promotionAndPeriodNames;
    private List<AmountPerX> amountPerPeriodRaports;
    private List<AmountPerX> amountPerPromotionRaports;
    private int noOfRecords;

    public IncomeRaportWrapper() {
    }

    public IncomeRaportWrapper(PromotionAndPeriodNames promotionAndPeriodNames, List<AmountPerX> amountPerPeriodRaports, List<AmountPerX> amountPerPromotionRaports, int noOfRecords) {
        this.promotionAndPeriodNames = promotionAndPeriodNames;
        this.amountPerPeriodRaports = amountPerPeriodRaports;
        this.amountPerPromotionRaports = amountPerPromotionRaports;
        this.noOfRecords = noOfRecords;
    }

    public List<AmountPerX> getAmountPerPeriodRaports() {
        return amountPerPeriodRaports;
    }

    public PromotionAndPeriodNames getPromotionAndPeriodNames() {
        return promotionAndPeriodNames;
    }

    public List<AmountPerX> getAmountPerPromotionRaports() {
        return amountPerPromotionRaports;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
