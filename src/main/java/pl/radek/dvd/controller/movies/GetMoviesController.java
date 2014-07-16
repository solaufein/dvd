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
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.validation.Valid;

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

        logger.info("Movies Controller");

        modelAndView = new ModelAndView("/movies/movies_list");
        return modelAndView;
    }

}
