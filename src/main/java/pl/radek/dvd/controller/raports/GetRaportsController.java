package pl.radek.dvd.controller.raports;

import org.apache.log4j.Logger;
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
import pl.radek.dvd.dto.raports.FilterTopHitsForm;
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.dto.raports.TopHitsDto;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.raports.RaportsFacade;

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
                                                ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);
        listDataRequest = new ListDataRequest(null, null, paginationInfo);

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

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

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
        }

        //todo: validacja daty od i do.. aby byly puste lub obie pelne.
        //todo: total amount    V
        //todo: zrobic FilterInfo aby akceptowal nie tylko Stringa(moze Object?)    V
        //todo: mysql dao - sprawdzic poprawnosc zapytania - uzyc Criterion ?   V
        //todo: paginacja wraz z filtracja  V

        if (!filterInfoList.isEmpty()) {
            listDataRequest = new ListDataRequest(null, filterInfoList, paginationInfo);

            PaginatedRaportList<TopHitsDto> topHitsDtoList = (PaginatedRaportList<TopHitsDto>) raportsFacade.getTopHitsDtoList(listDataRequest);
            List<TopHitsDto> dataList = topHitsDtoList.getDataList();

            int loanCount = topHitsDtoList.getTotalRecords();
            int noOfRecords = topHitsDtoList.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
            logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
            logger.info(" !!!! NO OF PAGES : " + noOfPages);
            logger.info(" !!!! Loan Count : " + loanCount);

            modelMap.addAttribute("loanCount", loanCount);
            modelMap.addAttribute("dataList", dataList);
            modelMap.addAttribute(Constants.CURRENTPAGE, page);
            modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);
            //     modelMap.addAttribute("tophits", topHitsForm);
        }
        modelMap.addAttribute("tophits", topHitsForm);

        return "/raports/raports_top_hits";
    }
}
