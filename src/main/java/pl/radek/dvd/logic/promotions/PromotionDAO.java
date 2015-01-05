package pl.radek.dvd.logic.promotions;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Promotion;

import java.util.List;

public interface PromotionDAO {
    public List<Promotion> getPromotions();

    public List<Promotion> getPromotions(ListDataRequest listDataRequest);

    public Promotion getPromotion(int id);

    public void deletePromotion(int id);

    public void addPromotion(Promotion promotion);

    public void updatePromotion(Promotion promotion);

    public int getNoOfRecords(ListDataRequest listDataRequest);
}
