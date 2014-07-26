package pl.radek.dvd.logic.promotions;

import pl.radek.dvd.model.Promotion;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:14
 */
public interface PromotionDAO {
    public List<Promotion> getPromotions();
    public Promotion getPromotion(int id);
    public void deletePromotion(int id);
    public void addPromotion(Promotion promotion);
    public void updatePromotion(Promotion promotion);
}
