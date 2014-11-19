package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 15:25
 */

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    @RequestMapping(value = "/emp/home", method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/emp/clients/clientslist");
      //  ModelAndView modelAndView = new ModelAndView("redirect:/emp/administration/administration");

        return modelAndView;
    }
}
