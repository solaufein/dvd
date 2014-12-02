package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.dto.clients.FiltreClientForm;
import pl.radek.dvd.dto.movies.MovieBySerialData;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.exceptions.movie.MovieNotFoundException;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.service.home.HomeFacade;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 15:25
 */

@Controller
@RequestMapping("/emp/home")
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    HomeFacade homeFacade;


    public void setHomeFacade(HomeFacade homeFacade) {
        this.homeFacade = homeFacade;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomeView(ModelMap modelMap) throws Exception {

        return "/home/home";
    }

    @RequestMapping(value = "/find/clientName", method = RequestMethod.GET)
    public String findClientByLastName(@RequestParam(value = "lastName") String lastName,
                                       ModelMap modelMap) throws Exception {

        try {
            List<Client> client = homeFacade.getClientByLastName(lastName);
        } catch (ClientNotFoundException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "/home/home";
        }

        return "redirect:/emp/clients/clientslist?lastName=" + lastName;
    }

    @RequestMapping(value = "/find/clientPesel", method = RequestMethod.GET)
    public String findClientByPesel(@RequestParam(value = "pesel") String pesel,
                                    ModelMap modelMap) throws Exception {

        try {
            List<ClientRentDto> client = homeFacade.getClients(pesel);
        } catch (ClientNotFoundException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "/home/home";
        }

        return "redirect:/emp/clients/clientslist?pesel=" + pesel;
    }

    @RequestMapping(value = "/find/movieTitle", method = RequestMethod.GET)
    public String findMovieByTitle(@RequestParam(value = "title") String title,
                                   ModelMap modelMap) throws Exception {

        try {
            List<Movie> movieByTitle = homeFacade.getMovieByTitle(title);
        } catch (MovieNotFoundException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "/home/home";
        }

        return "redirect:/emp/movies/movieslist?title=" + title;
    }

    @RequestMapping(value = "/find/movieSerialNumber", method = RequestMethod.GET)
    public String findMovieBySerialNumber(@RequestParam(value = "serialNumber") String serialNumber,
                                          ModelMap modelMap) throws Exception {
        MovieBySerialData movie;
        try {
            movie = homeFacade.getMovieBySerialNumber(serialNumber);
        } catch (MovieNotFoundException e) {
            modelMap.addAttribute("error", e.getMessage());
            return "/home/home";
        }

        return "redirect:/emp/movies/moviedetails?id=" + movie.getId();
    }


}
