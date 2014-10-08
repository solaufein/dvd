package pl.radek.dvd.controller.administration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.promotions.PromotionsFacade;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-11
 * Time: 16:42
 */

@Controller
@RequestMapping("/emp/administration/promotions")
public class GetPromotionsController {
    private static Logger logger = Logger.getLogger(GetPromotionsController.class);

    @Autowired
    private PromotionsFacade promotionsFacade;

    public void setPromotionsFacade(PromotionsFacade promotionsFacade) {
        this.promotionsFacade = promotionsFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
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
        logger.info(" !!!! PROMOTIONS !!!! ");
        logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
        logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
        logger.info(" !!!! NO OF PAGES : " + noOfPages);

        // follow to promotions.jsp
        logger.info("Follow to promotions jsp");
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);
        modelMap.addAttribute(Constants.PROMOTIONLIST, promotionDataList);


        return "/administration/promotions";
    }
}
