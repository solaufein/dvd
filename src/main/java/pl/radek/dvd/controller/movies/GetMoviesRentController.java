package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.actor.ActorDataTag;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.FiltreMovieForm;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.MoviesRentData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emp/clients/clientdetails/moviesrent")
public class GetMoviesRentController {
    private static Logger logger = Logger.getLogger(GetMoviesRentController.class);

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
    public ModelAndView handleRequest(@RequestParam(value = Constants.ID) int id,
                                      @RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                      @ModelAttribute("movie") @Valid FiltreMovieForm movie, BindingResult result) throws Exception {
        ModelAndView modelAndView;
        List<MoviesRentData> moviesDataList;
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
                logger.info("title = " + title);
            }
            if (genre != null && !genre.equals("") && !genre.equals("NONE")) {
                filterInfoList.add(new FilterInfo(Constants.GENRE, genre));
                logger.info("genre = " + genre);
            }
            if (promotion != null && !promotion.equals("") && !promotion.equals("NONE")) {
                filterInfoList.add(new FilterInfo(Constants.PROMOTION, promotion));
                logger.info("promotion = " + promotion);
            }
            if (actor != null && !actor.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.ACTOR, actor));
                logger.info("actor = " + actor);
            }

        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

        }

        ClientData clientData = moviesFacade.getClient(id);
        listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);
        PaginatedList<MoviesRentData> paginatedList = moviesFacade.getMoviesRentData(listDataRequest);
        int noOfRecords = paginatedList.getNoOfRecords();
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
        logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
        logger.info(" !!!! NO OF PAGES : " + noOfPages);
        moviesDataList = paginatedList.getDataList();

        modelAndView = new ModelAndView("/movies/movies_rent");
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.MOVIESLIST, moviesDataList);
        modelAndView.addObject(Constants.MOVIE, movie);
        modelAndView.addObject(Constants.CLIENT, clientData);
        return modelAndView;
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<ActorDataTag> getTags(@RequestParam("term") String term) {

        List<ActorDataTag> data = moviesFacade.getActorTags(term);

        for (int i = 0; i < data.size(); i++) {
            logger.info("data id = " + data.get(i).getId());
            logger.info("data tag = " + data.get(i).getTag());
        }

        return data;

    }
}
