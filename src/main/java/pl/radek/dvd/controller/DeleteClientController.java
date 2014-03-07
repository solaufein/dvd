package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.SimpleClientsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/delete.htm")
public class DeleteClientController {
    private static Logger logger = Logger.getLogger(DeleteClientController.class);

    @Autowired
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    @RequestMapping(method= RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam("id") int id) throws Exception {
        //     resp.setContentType("text/html");

        // get id from clients_list.jsp form
        logger.info("get id from clients_list.jsp form, id=" + id);

        logger.info("Deleting client with id=" + id);
        simpleClientsService.deleteClient(id);


        // redirect to GetClientsController
        logger.info("Redirect to GetClientsController");

        ModelAndView modelAndView = new ModelAndView("redirect:/clients.htm");

        return modelAndView;
    }
}
