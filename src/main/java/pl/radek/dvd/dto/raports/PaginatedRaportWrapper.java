package pl.radek.dvd.dto.raports;

import java.util.List;

public interface PaginatedRaportWrapper<T> {
    public List<T> getAmountPerPeriodRaports();
    public PromotionAndPeriodNames getPromotionAndPeriodNames();
    public List<T> getAmountPerPromotionRaports();
    public int getNoOfRecords();
}
