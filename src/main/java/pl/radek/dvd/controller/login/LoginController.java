package pl.radek.dvd.controller.login;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 13:35
 */

@Controller
public class LoginController {
    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "changed", required = false) String changed) {

        ModelAndView model = new ModelAndView("/login");
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }

        if (changed != null) {
            model.addObject("changed", "Your password has been changed successfully.");
        }

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
