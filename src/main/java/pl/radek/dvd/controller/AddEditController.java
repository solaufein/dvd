package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.SimpleClientsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/controller.htm")
public class AddEditController {
    private static Logger logger = Logger.getLogger(AddEditController.class);

    @Autowired
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(Constants.ID) String id) throws Exception {
        //     resp.setContentType("text/html");
        ModelAndView modelAndView;
        logger.info("get id from clients_list.jsp form, id=" + id);

        if (id.equals("new")) {          // new client
            logger.info("id equals: new (new client)");

            // follow to add_client.jsp
            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.ID, id);
        } else {                         // edit client with given id
            logger.info("id equals: " + id + "(edit client)");
            Client client = simpleClientsService.getClient(Integer.parseInt(id));

            // follow to add_client.jsp
            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.CLIENT, client);
        }
        return modelAndView;
    }
}
