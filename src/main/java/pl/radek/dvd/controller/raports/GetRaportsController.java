package pl.radek.dvd.controller.raports;

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
import pl.radek.dvd.dto.raports.MovieNotReturnedDto;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.raports.RaportsFacade;

import java.util.List;

@Controller
@RequestMapping("/emp/raports")
public class GetRaportsController {
    private static Logger logger = Logger.getLogger(GetRaportsController.class);

    @Autowired
    private RaportsFacade raportsFacade;

    public void setRaportsFacade(RaportsFacade raportsFacade) {
        this.raportsFacade = raportsFacade;
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
}
