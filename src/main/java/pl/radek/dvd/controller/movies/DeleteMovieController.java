package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.service.movies.MoviesFacade;

/**
 * User: Sola
 * Date: 2014-08-07
 * Time: 15:06
 */

@Controller
@RequestMapping("/emp/movies/delete")
@Secured("ROLE_ADMIN")
public class DeleteMovieController {
    private static Logger logger = Logger.getLogger(DeleteMovieController.class);

    @Autowired
    MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam("id") int id) throws Exception {
        //     resp.setContentType("text/html");

        // get id from movies_list.jsp form
        logger.info("get id from movies_list.jsp form, id=" + id);

        logger.info("Deleting movie with id=" + id);
        moviesFacade.deleteMovie(id);


        // redirect to GetMoviesController
        logger.info("Redirect to GetMoviesController");

        ModelAndView modelAndView = new ModelAndView("redirect:/emp/movies/movieslist");

        return modelAndView;
    }
}
