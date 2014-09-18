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

    @RequestMapping(value = "/emp/home", method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {

        ModelAndView modelAndView = new ModelAndView("redirect:/emp/clients/clientslist");

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
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

    @RequestMapping(value = "/remind", method = RequestMethod.GET)
    public ModelAndView pwReminderForm(
            @RequestParam(value = "msg", required = false) String msg) {

        ModelAndView model = new ModelAndView("/remind");
        if (msg != null) {
            model.addObject("msg", "Recovery link has been sent!");
        }

        return model;

    }

    @RequestMapping(value = "/remind", method = RequestMethod.POST)
    public ModelAndView sendRecoveryLink() {

        //todo: 1. logika, tworzenie kodu 20znakowego, sprawdzenie i zapis do bazy(kodu i daty), wyslanie maila z linkiem

        //todo: 2. zrobic Controller ktory obsluzy tego linka (w zaleznosci: jesli przekroczony 5min czas- to msg i nic)
        //todo:    a jesli mozna zmienic, to widok do zmiany. Zmiana -> controller, zapis do bazy nowego hasla, redirect Login.page

        ModelAndView model = new ModelAndView("/remind");
        model.addObject("msg", "Recovery link has been sent!");

        return model;

    }

    @RequestMapping(value = "/logout")
    public ModelAndView logout() {
        ModelAndView modelAndView = new ModelAndView("redirect:/logout");

        return modelAndView;
    }

    @RequestMapping(value = "/denied")
    public ModelAndView denied() {
        ModelAndView modelAndView = new ModelAndView("/denied");

        return modelAndView;
    }
}
