package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.logic.FormValidator;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.ClientFacadeImpl;
import pl.radek.dvd.service.SimpleClientsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/addclient.htm")
public class AddClientController {

    private static Logger logger = Logger.getLogger(AddClientController.class);

    @Autowired
    private ClientFacadeImpl clientFacade;

    public void setClientFacade(ClientFacadeImpl clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("client") @Valid Client client, BindingResult result) throws Exception {

        ModelAndView modelAndView;
        logger.info("AddClient - Get request parameters(fields)");

        if (!result.hasErrors()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");
            logger.info("Adding client to db");
            clientFacade.addClient(client);

            // redirect to GetClientsListServlet
            logger.info("Redirect to GetClientsController");
            modelAndView = new ModelAndView("redirect:/clients.htm");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.ID, client.getId());
            modelAndView.addObject(Constants.CLIENT, client);
        }
        return modelAndView;
    }
}
