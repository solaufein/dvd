package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.model.Constants;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 15:25
 */

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/emp/home.htm", method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/emp/clients.htm");

        return modelAndView;
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView("/login");
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        return model;

    }

    @RequestMapping(value="/logout.htm")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");

        return modelAndView;
    }

    @RequestMapping(value="/denied.htm")
    public ModelAndView denied(){
        ModelAndView modelAndView = new ModelAndView("/denied");

        return modelAndView;
    }
}
