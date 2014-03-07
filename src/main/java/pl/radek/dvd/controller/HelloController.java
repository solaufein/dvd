package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 04.03.14
 * Time: 17:01
 * To change this template use File | Settings | File Templates.
 */

public class HelloController implements Controller {

    private static Logger logger = Logger.getLogger(HelloController.class);

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        logger.info("Returning hello view");

        String now = new Date().toString();
        return new ModelAndView("jsp/hello.jsp", "now", now);
    }

}