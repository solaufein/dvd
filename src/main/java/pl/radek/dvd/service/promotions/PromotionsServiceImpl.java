package pl.radek.dvd.service.promotions;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.promotions.PaginatedListPromotion;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.logic.promotions.PromotionDAO;
import pl.radek.dvd.model.Promotion;

import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class PromotionsServiceImpl implements PromotionsService {

    private static Logger logger = Logger.getLogger(PromotionsServiceImpl.class);

    @Autowired
    private PromotionDAO promotionDAO;

    public void setPromotionDAO(PromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    @Override
    public List<PromotionData> getPromotions() {
        List<Promotion> promotions = promotionDAO.getPromotions();
        List<PromotionData> promotionDatas = convertPromotionListToPromotionDataList(promotions);
        return promotionDatas;
    }

    @Override
    public PaginatedList<PromotionData> getPromotions(ListDataRequest listDataRequest) {
        List<Promotion> promotions = promotionDAO.getPromotions(listDataRequest);
        int noOfRecords = promotionDAO.getNoOfRecords(listDataRequest);

        PaginatedListPromotion paginatedListPromotion = new PaginatedListPromotion();
        paginatedListPromotion.setPromotionDataList(convertPromotionListToPromotionDataList(promotions));
        paginatedListPromotion.setNoOfRecords(noOfRecords);

        return paginatedListPromotion;
    }

    @Override
    public List<Promotion> getPromotionsEntity() {
        return promotionDAO.getPromotions();
    }

    @Override
    public PromotionData getPromotion(int id) {
        Promotion promotion = promotionDAO.getPromotion(id);
        PromotionData promotionData = convertPromotionToPromotionData(promotion);
        return promotionData;
    }

    @Override
    public void deletePromotion(int id) {
        promotionDAO.deletePromotion(id);
    }

    @Override
    public void addPromotion(PromotionData promotion) {
        Promotion pro = convertPromotionDataToPromotion(promotion);
        promotionDAO.addPromotion(pro);
    }

    @Override
    public void updatePromotion(PromotionData promotion) {
        Promotion pro = convertPromotionDataToPromotion(promotion);
        promotionDAO.updatePromotion(pro);
    }

    private Promotion convertPromotionDataToPromotion(PromotionData promotionData) {
        Promotion promotion = new Promotion();
        promotion.setId(promotionData.getId());
        promotion.setName(promotionData.getName());
        promotion.setValue(promotionData.getValue());
        promotion.setPromotionDaysNumber(promotionData.getPromotionDaysNumber());
        promotion.setMovies(promotionData.getMovies());
        return promotion;
    }

    private PromotionData convertPromotionToPromotionData(Promotion promotion) {
        PromotionData promotionData = new PromotionData();
        promotionData.setId(promotion.getId());
        promotionData.setName(promotion.getName());
        promotionData.setValue(promotion.getValue());
        promotionData.setPromotionDaysNumber(promotion.getPromotionDaysNumber());
        promotionData.setMovies(promotion.getMovies());
        return promotionData;
    }

    private List<PromotionData> convertPromotionListToPromotionDataList(List<Promotion> promotions) {
        List<PromotionData> promotionDatas = new LinkedList<PromotionData>();
        for (Promotion promotion : promotions) {
            PromotionData promotionData = convertPromotionToPromotionData(promotion);
            promotionDatas.add(promotionData);
        }
        return promotionDatas;
    }
}
