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

    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/clients.htm");

        return modelAndView;
    }

    @RequestMapping(value="/login.htm")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("forward:/jsp/login.jsp");

        return modelAndView;
    }

    @RequestMapping(value="/logout.htm")
    public ModelAndView logout(){
        ModelAndView modelAndView = new ModelAndView("redirect:/jsp/logout.jsp");

        return modelAndView;
    }

    @RequestMapping(value="/denied.htm")
    public ModelAndView denied(){
        ModelAndView modelAndView = new ModelAndView("forward:/jsp/denied.jsp");

        return modelAndView;
    }

    //todo: redirect / forward / String and nothing ?
}
