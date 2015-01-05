package pl.radek.dvd.service.promotions;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Promotion;

import java.util.List;

public interface PromotionsService {
    public List<PromotionData> getPromotions();
    public PaginatedList<PromotionData> getPromotions(ListDataRequest listDataRequest);
    public List<Promotion> getPromotionsEntity();
    public PromotionData getPromotion(int id);
    public void deletePromotion(int id);
    public void addPromotion(PromotionData promotion);
    public void updatePromotion(PromotionData promotion);
}
