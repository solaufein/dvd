package pl.radek.dvd.service.raports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.*;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.genres.GenresService;
import pl.radek.dvd.service.promotions.PromotionsService;
import pl.radek.dvd.utils.UtilJavaMethods;

import java.util.*;

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
    public PaginatedRaportWrapper<AmountPerX> getIncomeWrappedList(ListDataRequest listDataRequest) {
        String formatPattern = getFormatPattern(listDataRequest);

        PaginatedList<IncomePromotionDTO> incomeDtoList = raportsService.getIncomeDtoList(listDataRequest);
        List<IncomePromotionDTO> dataList = incomeDtoList.getDataList();
        int noOfRecords = incomeDtoList.getNoOfRecords();

        IncomeRaportWrapper importRaportWrapper = createImportRaportWrapper(dataList, formatPattern, noOfRecords);

        return importRaportWrapper;
    }

    @Override
    public PaginatedRaportWrapper<AmountPerX> getRentPromotionWrappedList(ListDataRequest listDataRequest) {
        String formatPattern = getFormatPattern(listDataRequest);

        PaginatedList<IncomePromotionDTO> incomeDtoList = raportsService.getRentPromotionDtoList(listDataRequest);
        List<IncomePromotionDTO> dataList = incomeDtoList.getDataList();
        int noOfRecords = incomeDtoList.getNoOfRecords();

        IncomeRaportWrapper importRaportWrapper = createImportRaportWrapper(dataList, formatPattern, noOfRecords);

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

    private IncomeRaportWrapper createImportRaportWrapper(List<IncomePromotionDTO> dataList, String formatPattern, int noOfRecords) {
        PromotionAndPeriodNames promotionAndPeriod = getPromotionAndPeriod(dataList, formatPattern);
        List<AmountPerX> amountPerPeriodRaports = getAmountPerPeriodRaports(dataList, formatPattern, promotionAndPeriod);
        List<AmountPerX> amountPerPromotionRaports = getAmountPerPromotionRaports(dataList, promotionAndPeriod);

        return new IncomeRaportWrapper(promotionAndPeriod, amountPerPeriodRaports, amountPerPromotionRaports, noOfRecords);
    }

    private List<AmountPerX> getAmountPerPeriodRaports(List<IncomePromotionDTO> dataList, String formatPattern, PromotionAndPeriodNames promotionAndPeriod) {
        Number[] amount;
        AmountPerPeriodRaport amountPerPeriodRaport;
        List<AmountPerX> amountPerPeriodRaports = new ArrayList<AmountPerX>();
        Set<String> periodNames = promotionAndPeriod.getPeriodNames();
        Set<String> promotionNames = promotionAndPeriod.getPromotionNames();

        for (String period : periodNames) {     // year, month, day
            amountPerPeriodRaport = new AmountPerPeriodRaport();
            amount = new Number[promotionNames.size()];

            for (int i = 0; i < dataList.size(); i++) {
                IncomePromotionDTO incomePromotionDTO = dataList.get(i);

                String promotion = incomePromotionDTO.getPromotion();
                Number count = incomePromotionDTO.getCount();
                Date currentPeriod = incomePromotionDTO.getPeriod();
                String currPeriod = UtilJavaMethods.formatDate(formatPattern, currentPeriod);

                if (period.equals(currPeriod)) {     // adding to the same
                    int c = 0;
                    for (String promotionName : promotionNames) {
                        if (promotion.equals(promotionName)) {
                            amount[c++] = count;
                            amountPerPeriodRaport.setName(period);
                            amountPerPeriodRaport.setAmount(amount);
                        }
                        c++;
                    }
                }
            }
            amountPerPeriodRaports.add(amountPerPeriodRaport);
        }
        return amountPerPeriodRaports;
    }

    private List<AmountPerX> getAmountPerPromotionRaports(List<IncomePromotionDTO> dataList, PromotionAndPeriodNames promotionAndPeriod) {
        Number[] amount;
        AmountPerPromotionRaport amountPerPromotionRaport;
        List<AmountPerX> amountPerPromotionRaports = new ArrayList<AmountPerX>();
        Set<String> periodNames = promotionAndPeriod.getPeriodNames();
        Set<String> promotionNames = promotionAndPeriod.getPromotionNames();

        for (String promotion : promotionNames) {
            amountPerPromotionRaport = new AmountPerPromotionRaport();
            amount = new Number[periodNames.size()];
            int c = 0;

            for (int i = 0; i < dataList.size(); i++) {
                IncomePromotionDTO incomePromotionDTO = dataList.get(i);

                Number count = incomePromotionDTO.getCount();
                String currentPromotion = incomePromotionDTO.getPromotion();

                if (promotion.equals(currentPromotion)) {     // adding to the same
                    amount[c++] = count;
                    amountPerPromotionRaport.setName(promotion);
                    amountPerPromotionRaport.setAmount(amount);
                }
            }
            amountPerPromotionRaports.add(amountPerPromotionRaport);
        }
        return amountPerPromotionRaports;
    }

    private PromotionAndPeriodNames getPromotionAndPeriod(List<IncomePromotionDTO> dataList, String formatPattern) {
        Set<String> promotionNames = new LinkedHashSet<String>();
        Set<String> periodNames = new LinkedHashSet<String>();

        for (IncomePromotionDTO incomePromotionDTO : dataList) {
            String promotion = incomePromotionDTO.getPromotion();
            promotionNames.add(promotion);

            Date period = incomePromotionDTO.getPeriod();
            String formattedPeriod = UtilJavaMethods.formatDate(formatPattern, period);
            periodNames.add(formattedPeriod);
        }

        return new PromotionAndPeriodNames(promotionNames, periodNames);
    }

    private String getFormatPattern(ListDataRequest listDataRequest) {
        String section = null;
        List<FilterInfo> filterInfo = listDataRequest.getFilterInfo();
        for (FilterInfo info : filterInfo) {
            if (info.getFilterColumn().equals(Constants.SECTION)) {
                section = String.valueOf(info.getFilterData());
            }
        }

        String formatPattern;
        if (section.equals("MONTH")) {
            formatPattern = "yyyy-MMMM";
        } else if (section.equals("DAY")) {
            formatPattern = "yyyy-MM-dd";
            //    formatPattern = "yyyy-dd";
        } else if (section.equals("YEAR")) {
            formatPattern = "yyyy";
        } else {
            formatPattern = "yyyy-MM-dd";
        }

        return formatPattern;
    }
}
