package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.service.movies.MoviesFacade;

/**
 * User: Sola
 * Date: 2014-09-03
 * Time: 16:27
 */

@Controller
@RequestMapping("/emp/movies/deletecopy.htm")
public class DeleteMovieCopyController {

    private static Logger logger = Logger.getLogger(DeleteMovieCopyController.class);

    @Autowired
    private MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String delete(@RequestParam("id") int id,
                         @RequestParam("movieid") int movieid,
                         ModelMap modelMap) throws Exception {

        // get id from movies_list.jsp form
        logger.info("get id from movie_details.jsp form, id=" + id);

        logger.info("Deleting movie copy with id=" + id);
        moviesFacade.deleteMovieCopy(id);

        modelMap.addAttribute("id", movieid);

        // redirect to MovieDetailsController
        logger.info("Redirect to MovieDetailsController");

        return "redirect:/emp/movies/moviedetails.htm";
    }
}
