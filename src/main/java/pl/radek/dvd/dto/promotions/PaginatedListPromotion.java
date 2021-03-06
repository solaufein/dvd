package pl.radek.dvd.dto.promotions;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

public class PaginatedListPromotion implements PaginatedList<PromotionData> {
    private List<PromotionData> promotionDataList;
    private int noOfRecords;

    public void setPromotionDataList(List<PromotionData> promotionDataList) {
        this.promotionDataList = promotionDataList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public List<PromotionData> getDataList() {
        return promotionDataList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }
}
