package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

/**
 * User: Sola
 * Date: 2014-09-03
 * Time: 17:34
 */

@Controller
@RequestMapping("/emp/movies/register.htm")
public class RegisterMovieCopyController {
    private static Logger logger = Logger.getLogger(RegisterMovieCopyController.class);

    @Autowired
    MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(Constants.ID) String id) throws Exception {
        ModelAndView modelAndView;
        logger.info("get id from movies_list.jsp form, id=" + id);

        // follow to add_moviecopy.jsp
        modelAndView = new ModelAndView("/movies/add_moviecopy");
        MovieDataDTO movie = new MovieDataDTO();
        //     Movie movie = new Movie();
        movie.setId(-1);
        modelAndView.addObject(Constants.MOVIE, movie);
        //     modelAndView.addObject(Constants.ID, id);


        return modelAndView;
    }
}
