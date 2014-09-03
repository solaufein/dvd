package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.validation.Valid;

/**
 * User: Sola
 * Date: 2014-09-03
 * Time: 17:05
 */

@Controller
@RequestMapping("/emp/movies/addmoviecopy.htm")
public class AddMovieCopyController {
    private static Logger logger = Logger.getLogger(AddMovieCopyController.class);

    @Autowired
    private MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(@ModelAttribute("moviecopy") @Valid MovieCopyDTO movie,
                                @RequestParam("movieid") int movieid,
                                BindingResult result,
                                ModelMap modelMap) throws Exception {

        logger.info("AddMovieCopy - Get request parameters(fields)");

        if (!result.hasErrors()) {

        } else {

        }


        modelMap.addAttribute("id", movieid);

        // redirect to MovieDetailsController
        logger.info("Redirect to MovieDetailsController");

        return "redirect:/emp/movies/moviedetails.htm";
    }
}
