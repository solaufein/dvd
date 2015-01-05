package pl.radek.dvd.service.promotions;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.promotions.PromotionData;

public interface PromotionsFacade {
    public PaginatedList<PromotionData> getPromotions(ListDataRequest listDataRequest);
    public PromotionData getPromotion(int id);
    public void deletePromotion(int id);
    public void addPromotion(PromotionData promotion);
    public void updatePromotion(PromotionData promotion);
}
