package pl.radek.dvd.service.promotions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.promotions.PromotionData;

/**
 * User: Sola
 * Date: 2014-10-08
 * Time: 12:43
 */

@Component
public class PromotionsFacadeImpl implements PromotionsFacade {
    private static Logger logger = Logger.getLogger(PromotionsFacadeImpl.class);

    @Autowired
    private PromotionsService promotionsService;

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @Override
    public PaginatedList<PromotionData> getPromotions(ListDataRequest listDataRequest) {
        return promotionsService.getPromotions(listDataRequest);
    }

    @Override
    public PromotionData getPromotion(int id) {
        return promotionsService.getPromotion(id);
    }

    @Override
    public void deletePromotion(int id) {
        promotionsService.deletePromotion(id);
    }

    @Override
    public void addPromotion(PromotionData promotion) {
        promotionsService.addPromotion(promotion);
    }

    @Override
    public void updatePromotion(PromotionData promotion) {
        promotionsService.updatePromotion(promotion);
    }
}
