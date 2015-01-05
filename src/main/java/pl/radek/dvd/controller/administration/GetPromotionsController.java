package pl.radek.dvd.controller.administration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.promotions.PromotionsFacade;

import java.util.List;

@Controller
@RequestMapping("/emp/administration/promotions")
public class GetPromotionsController {
    private static Logger logger = Logger.getLogger(GetPromotionsController.class);

    @Autowired
    private PromotionsFacade promotionsFacade;

    public void setPromotionsFacade(PromotionsFacade promotionsFacade) {
        this.promotionsFacade = promotionsFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String handleRequest(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
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

        PaginatedList<PromotionData> promotionDataPaginatedList = promotionsFacade.getPromotions(listDataRequest);
        int noOfRecords = promotionDataPaginatedList.getNoOfRecords();
        List<PromotionData> promotionDataList = promotionDataPaginatedList.getDataList();

        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        PromotionData promotionData = new PromotionData();

        // follow to promotions.jsp
        logger.info("Follow to promotions jsp");
        modelMap.addAttribute(Constants.PROMOTION, promotionData);
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);
        modelMap.addAttribute(Constants.PROMOTIONLIST, promotionDataList);

        return "/administration/promotions";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getCurrentPromotionPage(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                          ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        } else {
            logger.info(" !!!! CurrentPage is NULL (0) ");
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        listDataRequest = new ListDataRequest(null, null, paginationInfo);

        PaginatedList<PromotionData> promotionDataPaginatedList = promotionsFacade.getPromotions(listDataRequest);
        List<PromotionData> promotionDataList = promotionDataPaginatedList.getDataList();

        // follow to promotions_page.jsp
        logger.info("Follow to promotions_page jsp");
        modelMap.addAttribute(Constants.PROMOTIONLIST, promotionDataList);

        logger.info(" !!!! PAGE : " + page);

        return "/administration/promotions_page";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deletePromotion(@RequestParam("id") int id) throws Exception {

        logger.info("Deleting promotion with id=" + id);

        promotionsFacade.deletePromotion(id);

        logger.info("Promotion with id = " + id + " successfully deleted");

        return "Promotion successfully deleted";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String newPromotion(@ModelAttribute("promotion") PromotionData promotion) throws Exception {
        logger.info("new Promotion controller method start - adding new promotion ");

        promotionsFacade.addPromotion(promotion);

        logger.info("new Promotion controller method end - added promotion ");

        return "Promotion successfully added";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public PromotionData getPromotionById(@RequestParam("id") int id) throws Exception {
        logger.info("get Promotion controller method start");

        PromotionData promotionData = promotionsFacade.getPromotion(id);
        promotionData.setMovies(null);  // we dont need/want it to json

        logger.info("get Promotion controller method end");

        return promotionData;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editPromotion(@ModelAttribute("promotion") PromotionData promotion) throws Exception {
        logger.info("edit Promotion controller method start - editing Promotion ");

        promotionsFacade.updatePromotion(promotion);

        logger.info("edit Promotion controller method end - Promotion edited ok ");

        return "Promotion successfully edited and saved";
    }
}
