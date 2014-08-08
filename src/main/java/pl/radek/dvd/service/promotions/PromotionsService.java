package pl.radek.dvd.service.promotions;

import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Promotion;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:27
 */
public interface PromotionsService {
    public List<PromotionData> getPromotions();
    public List<Promotion> getPromotionsEntity();
    public PromotionData getPromotion(int id);
    public void deletePromotion(int id);
    public void addPromotion(PromotionData promotion);
    public void updatePromotion(PromotionData promotion);
}
