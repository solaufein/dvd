package pl.radek.dvd.dto.raports;

import java.util.List;

/**
 * Created by Sola on 2014-12-17.
 */
public interface PaginatedRaportWrapper<T> {
    public List<T> getAmountPerPeriodRaports();
    public PromotionAndPeriodNames getPromotionAndPeriodNames();
    public List<T> getAmountPerPromotionRaports();
    public int getNoOfRecords();
}
