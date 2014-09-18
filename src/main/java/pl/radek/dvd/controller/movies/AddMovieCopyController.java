package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
@RequestMapping("/emp/movies/addmoviecopy")
public class AddMovieCopyController {
    private static Logger logger = Logger.getLogger(AddMovieCopyController.class);

    @Autowired
    private MoviesFacade moviesFacade;

    @Autowired
    private MessageSource messageSource;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(@ModelAttribute(Constants.MOVIECOPY) @Valid MovieCopyDTO moviecopy,
                                BindingResult result,
                                @RequestParam(Constants.MOVIEID) int movieid,
                                ModelMap modelMap) throws Exception {

        logger.info("AddMovieCopy - Get request parameters(fields)");

        if (!result.hasErrors()) {
            try {
                moviesFacade.addMovieCopy(movieid, moviecopy);
            } catch (Exception e) {
                modelMap.addAttribute(Constants.MOVIECOPY, moviecopy);
                modelMap.addAttribute(Constants.MOVIEID, movieid);

                String duplicate = messageSource.getMessage("Duplicate", null, LocaleContextHolder.getLocale());
                modelMap.addAttribute("duplicate", duplicate);

                return "/movies/add_moviecopy";
            }

            modelMap.addAttribute(Constants.ID, movieid);

            // redirect to MovieDetailsController
            logger.info("Redirect to MovieDetailsController");

            return "redirect:/emp/movies/moviedetails";

        } else {
            modelMap.addAttribute(Constants.MOVIECOPY, moviecopy);
            modelMap.addAttribute(Constants.MOVIEID, movieid);
            return "/movies/add_moviecopy";
        }
    }
}
