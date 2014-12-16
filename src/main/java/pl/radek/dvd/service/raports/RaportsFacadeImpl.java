package pl.radek.dvd.service.raports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.*;
import pl.radek.dvd.service.genres.GenresService;
import pl.radek.dvd.service.promotions.PromotionsService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class RaportsFacadeImpl implements RaportsFacade {
    private static Logger logger = Logger.getLogger(RaportsFacadeImpl.class);

    @Autowired
    RaportsService raportsService;

    @Autowired
    private GenresService genresService;

    @Autowired
    private PromotionsService promotionsService;

    public void setGenresService(GenresService genresService) {
        this.genresService = genresService;
    }

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    public void setRaportsService(RaportsService raportsService) {
        this.raportsService = raportsService;
    }

    @Override
    public PaginatedList<MovieNotReturnedDto> getMovieNotReturnedDtoList(ListDataRequest listDataRequest) {
        PaginatedList<MovieNotReturnedDto> movieNotReturnedDtoList = raportsService.getMovieNotReturnedDtoList(listDataRequest);

        return movieNotReturnedDtoList;
    }

    @Override
    public PaginatedList<TopHitsDto> getTopHitsDtoList(ListDataRequest listDataRequest) {
        return raportsService.getTopHitsDtoList(listDataRequest);
    }

    @Override
    public PaginatedList<IncomePromotionDTO> getIncomeDtoList(ListDataRequest listDataRequest) {
        return raportsService.getIncomeDtoList(listDataRequest);
    }

    @Override
    public PaginatedList<IncomePromotionDTO> getRentPromotionDtoList(ListDataRequest listDataRequest) {
        return raportsService.getRentPromotionDtoList(listDataRequest);
    }

    @Override
    public IncomeRaportWrapper getIncomeWrappedList(ListDataRequest listDataRequest) {
        PaginatedList<IncomePromotionDTO> incomeDtoList = raportsService.getIncomeDtoList(listDataRequest);
        List<IncomePromotionDTO> dataList = incomeDtoList.getDataList();
        int noOfRecords = incomeDtoList.getNoOfRecords();

        IncomeRaportWrapper importRaportWrapper = createImportRaportWrapper(dataList, noOfRecords);

        return importRaportWrapper;
    }

    @Override
    public List<GenreData> getGenres() {
        return genresService.getGenres();
    }

    @Override
    public List<PromotionData> getPromotions() {
        return promotionsService.getPromotions();
    }

    private IncomeRaportWrapper createImportRaportWrapper(List<IncomePromotionDTO> dataList, int noOfRecords) {
        Number[] amount;
        RaportDto raportDto;
        List<RaportDto> raportDtos = new ArrayList<RaportDto>();

        PromotionAndPeriodNames promotionAndPeriod = getPromotionAndPeriod(dataList);
        Set<Number> periodNames = promotionAndPeriod.getPeriodNames();
        Set<String> promotionNames = promotionAndPeriod.getPromotionNames();

        //    System.out.println("promotion names = " + promotionNames);
        //    System.out.println("period names = " + periodNames);

        for (Number period : periodNames) {
            raportDto = new RaportDto();
            amount = new Number[promotionNames.size()];

            for (int i = 0; i < dataList.size(); i++) {
                IncomePromotionDTO incomePromotionDTO = dataList.get(i);

                String promotion = incomePromotionDTO.getPromotion();
                Number count = incomePromotionDTO.getCount();
                Number currentPeriod = incomePromotionDTO.getPeriod();

                if (period.equals(currentPeriod)) {     // dodajemy do tego samego
                    int c = 0;
                    for (String promotionName : promotionNames) {
                        if (promotion.equals(promotionName)) {
                            amount[c++] = count;
                            raportDto.setPeriodName(period);
                            raportDto.setAmount(amount);
                        }
                        c++;
                    }
                }
            }
            raportDtos.add(raportDto);
        }
        return new IncomeRaportWrapper(promotionAndPeriod, raportDtos, noOfRecords);
    }

    private PromotionAndPeriodNames getPromotionAndPeriod(List<IncomePromotionDTO> dataList) {
        Set<String> promotionNames = new HashSet<String>();
        Set<Number> periodNames = new HashSet<Number>();

        for (IncomePromotionDTO incomePromotionDTO : dataList) {
            String promotion = incomePromotionDTO.getPromotion();
            promotionNames.add(promotion);

            Number period = incomePromotionDTO.getPeriod();
            periodNames.add(period);
        }

        return new PromotionAndPeriodNames(promotionNames, periodNames);
    }
}
