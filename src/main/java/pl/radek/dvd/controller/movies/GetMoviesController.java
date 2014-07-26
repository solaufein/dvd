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
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.FiltreMovieForm;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @ModelAttribute("allGenres")
    public List<GenreData> getAllGenres() {
        return moviesFacade.getGenres();
    }

    @ModelAttribute("allPromotions")
    public List<PromotionData> getAllPromotions() {
        return moviesFacade.getPromotions();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                      @ModelAttribute("movie") @Valid FiltreMovieForm movie, BindingResult result) throws Exception {
        ModelAndView modelAndView;
        List<MoviesData> moviesDataList;
        SortInfo sortInfo = null;
        List<FilterInfo> filterInfoList = null;
        ListDataRequest listDataRequest;

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

        if (!result.hasErrors()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");

            // Filter Info
            String title = movie.getTitle();
            String genre = movie.getGenre();
            String promotion = movie.getPromotion();
            String actor = movie.getActor();

            filterInfoList = new ArrayList<FilterInfo>();
            if (title != null && !title.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.TITLE, title));
            }
            if (genre != null && !genre.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.GENRE, genre));
            }
            if (promotion != null && !promotion.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.PROMOTION, promotion));
            }
            if (actor != null && !actor.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.ACTOR, actor));
            }

        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

        }

        listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);
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
        modelAndView.addObject(Constants.MOVIE, movie);
        return modelAndView;
    }

}
