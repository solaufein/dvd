package pl.radek.dvd.controller.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.actor.ActorDataTag;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.editor.movies.ActorCollectionsEditor;
import pl.radek.dvd.editor.movies.GenreEditor;
import pl.radek.dvd.editor.movies.PromotionEditor;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.movies.MoviesFacade;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-08-07
 * Time: 15:10
 */

@Controller
@RequestMapping("/emp/movies/editmovie")
public class EditMovieController {
    private static Logger logger = Logger.getLogger(EditMovieController.class);

    @Autowired
    MoviesFacade moviesFacade;

    public void setMoviesFacade(MoviesFacade moviesFacade) {
        this.moviesFacade = moviesFacade;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(GenreData.class, "genre", new GenreEditor(this.moviesFacade));
        binder.registerCustomEditor(PromotionData.class, "promotion", new PromotionEditor(this.moviesFacade));
        binder.registerCustomEditor(Set.class, "actorset", new ActorCollectionsEditor(Set.class, true, moviesFacade));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("movie") @Valid MovieDataDTO movie, BindingResult result) throws Exception {

        ModelAndView modelAndView;
        logger.info("EditMovie - Get request parameters(fields)");

        if (!result.hasErrors()) {
            // No errors, redirect to movies list
            logger.info("No errors spotted");
            logger.info("Editing movie with id = " + movie.getId());

            logger.info("Title = " + movie.getTitle());

            moviesFacade.updateMovie(movie);

            // redirect to GetMoviesController
            logger.info("Redirect to GetMoviesController");
            modelAndView = new ModelAndView("redirect:/emp/movies/movieslist");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            //  movie.setActorset(null);

            modelAndView = new ModelAndView("/movies/add_movie");
            modelAndView.addObject(Constants.ID, movie.getId());
            modelAndView.addObject(Constants.MOVIE, movie);
            modelAndView.addObject("allGenres", moviesFacade.getGenres());
            modelAndView.addObject("allPromotions", moviesFacade.getPromotions());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getTags", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    List<ActorDataTag> getTags(@RequestParam("term") String term) {

        List<ActorDataTag> data = moviesFacade.getActorTags(term);

        for (ActorDataTag aData : data) {
            logger.info("data id = " + aData.getId());
            logger.info("data tag = " + aData.getTag());
        }

        return data;
    }
}
