package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.Promotion;
import pl.radek.dvd.service.movies.MoviesFacade;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-08-07
 * Time: 13:43
 */

@Controller
@RequestMapping("/emp/movies/controller.htm")
public class AddEditMovieController {
    private static Logger logger = Logger.getLogger(AddEditMovieController.class);

    @Autowired
    MoviesFacade moviesFacade;

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

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(Constants.ID) String id) throws Exception {
        ModelAndView modelAndView;
        logger.info("get id from movies_list.jsp form, id=" + id);

        if (id.equals("new")) {          // new movie
            logger.info("id equals: new (new movie)");

            // follow to add_movie.jsp
            modelAndView = new ModelAndView("/movies/add_movie");
            MovieDataDTO movie = new MovieDataDTO();
            movie.setId(-1);
            modelAndView.addObject(Constants.MOVIE, movie);
            //     modelAndView.addObject(Constants.ID, id);

        } else {                         // edit movie with given id
            logger.info("id equals: " + id + "(edit movie)");
            MovieDataDTO movie = moviesFacade.getMovie(Integer.parseInt(id));

            // follow to add_movie.jsp
            modelAndView = new ModelAndView("/movies/add_movie");
            modelAndView.addObject(Constants.MOVIE, movie);
      //      logger.info("Genre name = " + movie.getGenre().getGenre());
            logger.info("Promotion name = " + movie.getPromotion().getName());
        }
        return modelAndView;
    }
}
