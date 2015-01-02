package pl.radek.dvd.controller.raports;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.dto.raports.*;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.raports.RaportsFacade;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/emp/raports")
public class GetRaportsController {
    private static Logger logger = Logger.getLogger(GetRaportsController.class);

    @Autowired
    private RaportsFacade raportsFacade;

    public void setRaportsFacade(RaportsFacade raportsFacade) {
        this.raportsFacade = raportsFacade;
    }

    @ModelAttribute("allGenres")
    public List<GenreData> getAllGenres() {
        return raportsFacade.getGenres();
    }

    @ModelAttribute("allPromotions")
    public List<PromotionData> getAllPromotions() {
        return raportsFacade.getPromotions();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRaportsView(ModelMap modelMap) throws Exception {

        return "/raports/raports_list";
    }

    @RequestMapping(value = "/notreturned", method = RequestMethod.GET)
    public String getNotReturnedMovieRaportView(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                                @ModelAttribute("moviesNotReturned") FilterMoviesNotReturnedForm moviesNotReturnedForm,
                                                ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;
        List<FilterInfo> filterInfoList;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        // Filter Info
        Date dateFrom = moviesNotReturnedForm.getDateFrom();
        Date dateTo = moviesNotReturnedForm.getDateTo();

        filterInfoList = new ArrayList<FilterInfo>();
        if (dateFrom != null && !dateFrom.equals("") && dateTo != null && !dateTo.equals("")) {
            Map<String, Date> range = new HashMap<String, Date>();
            range.put("from", dateFrom);
            range.put("to", dateTo);
            filterInfoList.add(new FilterInfo("date", range));
        } else {
            Map<String, Date> range = new HashMap<String, Date>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date from = cal.getTime();
            Date to = new Date();

            range.put("from", from);
            range.put("to", new Date());
            filterInfoList.add(new FilterInfo("date", range));

            moviesNotReturnedForm.setDateFrom(from);
            moviesNotReturnedForm.setDateTo(to);
        }

        listDataRequest = new ListDataRequest(null, filterInfoList, paginationInfo);

        PaginatedList<MovieNotReturnedDto> movieNotReturnedDtoList = raportsFacade.getMovieNotReturnedDtoList(listDataRequest);
        List<MovieNotReturnedDto> dataList = movieNotReturnedDtoList.getDataList();
        int noOfRecords = movieNotReturnedDtoList.getNoOfRecords();

        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
        logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
        logger.info(" !!!! NO OF PAGES : " + noOfPages);

        modelMap.addAttribute("dataList", dataList);
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);

        return "/raports/raports_not_returned";
    }

    @RequestMapping(value = "/tophits", method = RequestMethod.GET)
    public String getTopHitsRaportFormView(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                           @ModelAttribute("tophits") FilterTopHitsForm topHitsForm,
                                           ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;
        List<FilterInfo> filterInfoList;

        // Filter Info
        String promotion = topHitsForm.getPromotion();
        String genre = topHitsForm.getGenre();
        Date dateFrom = topHitsForm.getDateFrom();
        Date dateTo = topHitsForm.getDateTo();

        filterInfoList = new ArrayList<FilterInfo>();
        if (genre != null && !genre.equals("") && !genre.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.GENRE, genre));
            logger.info("genre = " + genre);
        }
        if (promotion != null && !promotion.equals("") && !promotion.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.PROMOTION, promotion));
            logger.info("promotion = " + promotion);
        }

        if (dateFrom != null && !dateFrom.equals("") && dateTo != null && !dateTo.equals("")) {
            Map<String, Date> range = new HashMap<String, Date>();
            range.put("from", dateFrom);
            range.put("to", dateTo);
            filterInfoList.add(new FilterInfo("date", range));
        } else {
            Map<String, Date> range = new HashMap<String, Date>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date from = cal.getTime();
            Date to = new Date();

            range.put("from", from);
            range.put("to", new Date());
            filterInfoList.add(new FilterInfo("date", range));

            topHitsForm.setDateFrom(from);
            topHitsForm.setDateTo(to);
        }

        listDataRequest = new ListDataRequest(null, filterInfoList, null);

        PaginatedRaportList<TopHitsDto> topHitsDtoList = (PaginatedRaportList<TopHitsDto>) raportsFacade.getTopHitsDtoList(listDataRequest);
        List<TopHitsDto> dataList = topHitsDtoList.getDataList();

        int loanCount = topHitsDtoList.getTotalRecords();
        logger.info(" !!!! Loan Count : " + loanCount);

        modelMap.addAttribute("loanCount", loanCount);
        modelMap.addAttribute("dataList", dataList);
        modelMap.addAttribute("tophits", topHitsForm);

        return "/raports/raports_top_hits";
    }

    @RequestMapping(value = "/income", method = RequestMethod.GET)
    public String getIncomeRaportFormView(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                          @ModelAttribute("income") IncomePromotionFormDto income,
                                          ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;
        List<FilterInfo> filterInfoList;

        // Filter Info
        String section = income.getSection();
        String genre = income.getGenre();
        Date dateFrom = income.getDateFrom();
        Date dateTo = income.getDateTo();

        filterInfoList = new ArrayList<FilterInfo>();
        if (genre != null && !genre.equals("") && !genre.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.GENRE, genre));
            logger.info("genre = " + genre);
        }
        if (section != null && !section.equals("") && !section.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.SECTION, section));
            logger.info("section = " + section);
        } else {
            filterInfoList.add(new FilterInfo(Constants.SECTION, "MONTH"));
            logger.info("section = MONTH");
        }

        if (dateFrom != null && !dateFrom.equals("") && dateTo != null && !dateTo.equals("")) {
            Map<String, Date> range = new HashMap<String, Date>();
            range.put("from", dateFrom);
            range.put("to", dateTo);
            filterInfoList.add(new FilterInfo("date", range));
        } else {
            Map<String, Date> range = new HashMap<String, Date>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date from = cal.getTime();
            Date to = new Date();

            range.put("from", from);
            range.put("to", new Date());
            filterInfoList.add(new FilterInfo("date", range));

            income.setDateFrom(from);
            income.setDateTo(to);
        }

        listDataRequest = new ListDataRequest(null, filterInfoList, null);

        PaginatedRaportWrapper<AmountPerX> incomeWrappedList = raportsFacade.getIncomeWrappedList(listDataRequest);
        List<AmountPerX> amountPerPeriodRaports = incomeWrappedList.getAmountPerPeriodRaports();
        List<AmountPerX> amountPerPromotionRaports = incomeWrappedList.getAmountPerPromotionRaports();

        PromotionAndPeriodNames promotionAndPeriodNames = incomeWrappedList.getPromotionAndPeriodNames();
        Set<String> promotionNames = promotionAndPeriodNames.getPromotionNames();
        Set<String> periodNames = promotionAndPeriodNames.getPeriodNames();

        BigDecimal bd = BigDecimal.ZERO;
        for (AmountPerX amountPerPeriodRaport : amountPerPeriodRaports) {
            bd = bd.add(new BigDecimal(amountPerPeriodRaport.getTotalAmount()));
        }

        modelMap.addAttribute("periodNames", periodNames);
        modelMap.addAttribute("promotionNames", promotionNames);
        modelMap.addAttribute("dataList", amountPerPeriodRaports);
        modelMap.addAttribute("amountPerPromotion", amountPerPromotionRaports);
        modelMap.addAttribute("total", bd.toString());

        List<String> sections = new ArrayList<String>();
        sections.add("MONTH");
        sections.add("YEAR");
        sections.add("DAY");

        modelMap.addAttribute("allSections", sections);
        modelMap.addAttribute("income", income);

        return "/raports/raports_income";
    }

    @RequestMapping(value = "/promotions", method = RequestMethod.GET)
    public String getRentPromotionRaportFormView(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                                 @ModelAttribute("promotions") IncomePromotionFormDto promotions,
                                                 ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;
        List<FilterInfo> filterInfoList;

        // Filter Info
        String section = promotions.getSection();
        String genre = promotions.getGenre();
        Date dateFrom = promotions.getDateFrom();
        Date dateTo = promotions.getDateTo();

        filterInfoList = new ArrayList<FilterInfo>();
        if (genre != null && !genre.equals("") && !genre.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.GENRE, genre));
            logger.info("genre = " + genre);
        }
        if (section != null && !section.equals("") && !section.equals("NONE")) {
            filterInfoList.add(new FilterInfo(Constants.SECTION, section));
            logger.info("section = " + section);
        } else {
            filterInfoList.add(new FilterInfo(Constants.SECTION, "MONTH"));
            logger.info("section = MONTH");
        }

        if (dateFrom != null && !dateFrom.equals("") && dateTo != null && !dateTo.equals("")) {
            Map<String, Date> range = new HashMap<String, Date>();
            range.put("from", dateFrom);
            range.put("to", dateTo);
            filterInfoList.add(new FilterInfo("date", range));
        } else {
            Map<String, Date> range = new HashMap<String, Date>();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            Date from = cal.getTime();
            Date to = new Date();

            range.put("from", from);
            range.put("to", new Date());
            filterInfoList.add(new FilterInfo("date", range));

            promotions.setDateFrom(from);
            promotions.setDateTo(to);
        }

        listDataRequest = new ListDataRequest(null, filterInfoList, null);

        PaginatedRaportWrapper<AmountPerX> rentPromotionWrappedList = raportsFacade.getRentPromotionWrappedList(listDataRequest);
        List<AmountPerX> amountPerPeriodRaports = rentPromotionWrappedList.getAmountPerPeriodRaports();
        List<AmountPerX> amountPerPromotionRaports = rentPromotionWrappedList.getAmountPerPromotionRaports();

        PromotionAndPeriodNames promotionAndPeriodNames = rentPromotionWrappedList.getPromotionAndPeriodNames();
        Set<String> promotionNames = promotionAndPeriodNames.getPromotionNames();
        Set<String> periodNames = promotionAndPeriodNames.getPeriodNames();

        BigDecimal bd = BigDecimal.ZERO;
        for (AmountPerX amountPerPeriodRaport : amountPerPeriodRaports) {
            bd = bd.add(new BigDecimal(amountPerPeriodRaport.getTotalAmount()));
        }

        modelMap.addAttribute("periodNames", periodNames);
        modelMap.addAttribute("promotionNames", promotionNames);
        modelMap.addAttribute("dataList", amountPerPeriodRaports);
        modelMap.addAttribute("amountPerPromotion", amountPerPromotionRaports);
        modelMap.addAttribute("total", bd.toString());

        List<String> sections = new ArrayList<String>();
        sections.add("MONTH");
        sections.add("YEAR");
        sections.add("DAY");

        modelMap.addAttribute("allSections", sections);
        modelMap.addAttribute("promotions", promotions);

        return "/raports/raports_rent_promotion";
    }
}
