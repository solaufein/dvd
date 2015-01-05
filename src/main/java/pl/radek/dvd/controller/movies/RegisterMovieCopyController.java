package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

@Controller
@RequestMapping("/emp/movies/register")
public class RegisterMovieCopyController {
    private static Logger logger = Logger.getLogger(RegisterMovieCopyController.class);

    @Autowired
    MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(@RequestParam(Constants.ID) String id,
                                @RequestParam(Constants.MOVIEID) String movieid,
                                ModelMap modelMap) throws Exception {
        ModelAndView modelAndView;
        logger.info("get id from movie_details.jsp id=" + id);
        logger.info("movie id=" + movieid);

        // follow to add_moviecopy.jsp
        MovieCopyDTO movieCopyDTO = new MovieCopyDTO();
        modelMap.addAttribute(Constants.MOVIECOPY, movieCopyDTO);
        modelMap.addAttribute(Constants.MOVIEID, movieid);

        return "/movies/add_moviecopy";
    }
}
