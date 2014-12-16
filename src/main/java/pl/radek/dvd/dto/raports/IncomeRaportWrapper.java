package pl.radek.dvd.dto.raports;

import java.util.List;

/**
 * Created by Sola on 2014-12-16.
 */
public class IncomeRaportWrapper {
    private PromotionAndPeriodNames promotionAndPeriodNames;
    private List<RaportDto> raportDtos;
    private int noOfRecords;

    public IncomeRaportWrapper() {
    }

    public IncomeRaportWrapper(PromotionAndPeriodNames promotionAndPeriodNames, List<RaportDto> raportDtos, int noOfRecords) {
        this.promotionAndPeriodNames = promotionAndPeriodNames;
        this.raportDtos = raportDtos;
        this.noOfRecords = noOfRecords;
    }

    public List<RaportDto> getRaportDtos() {
        return raportDtos;
    }

    public PromotionAndPeriodNames getPromotionAndPeriodNames() {
        return promotionAndPeriodNames;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }
}
