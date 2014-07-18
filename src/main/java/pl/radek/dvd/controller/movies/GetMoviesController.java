package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.validation.Valid;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-04
 * Time: 13:28
 */

@Controller
@RequestMapping("/emp/movies/movieslist.htm")
public class GetMoviesController {
    private static Logger logger = Logger.getLogger(GetMoviesController.class);

    @Autowired
    private MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage) throws Exception {
        ModelAndView modelAndView;
        List<MoviesData> moviesDataList;
        SortInfo sortInfo = null;
        List<FilterInfo> filterInfoList = null;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        // Sort Info
        if ((field != null) && (order != null)) {
            logger.info("Field = " + field);
            logger.info("Order = " + order);
            boolean isAscOrder;
            if (order.equals(Constants.ASC)) {
                isAscOrder = true;
            } else {
                isAscOrder = false;
            }

            sortInfo = new SortInfo(field, isAscOrder);
        }

        ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);
        PaginatedList<MoviesData> paginatedList = moviesFacade.getMovies(listDataRequest);
        int noOfRecords = paginatedList.getNoOfRecords();
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
        logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
        logger.info(" !!!! NO OF PAGES : " + noOfPages);
        moviesDataList = paginatedList.getDataList();

        modelAndView = new ModelAndView("/movies/movies_list");
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.MOVIESLIST, moviesDataList);
   //     modelAndView.addObject(Constants.MOVIE, movie);
        return modelAndView;
    }

}
