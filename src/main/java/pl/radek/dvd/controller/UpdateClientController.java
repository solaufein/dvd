package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.logic.FormValidator;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.SimpleClientsService;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/updateclient.htm")
public class UpdateClientController {
    private static Logger logger = Logger.getLogger(AddClientController.class);

    @Autowired
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("client") Client client) throws Exception {

        FormValidator formValidator = new FormValidator();
        Map<String, String> errors;
        ModelAndView modelAndView;

        logger.info("UpdateClient - Get request parameters(fields)");

        // validate form fields
        logger.info("Validate form fields");
        formValidator.validateAlfabeticField(client.getFirstName(), Constants.FIRSTNAME, " - Invalid Firstname");
        formValidator.validateAlfabeticField(client.getLastName(), Constants.LASTNAME, " - Invalid Lastname");
        formValidator.validatePesel(client.getPesel());
        formValidator.validateAlfabeticField(client.getCity(), Constants.CITY, " - Invalid City");
        formValidator.validateEmptyField(client.getStreet(), Constants.STREET, " - Invalid Street");
        formValidator.validateEmptyField(client.getPhoneNumber(), Constants.PHONENUMBER, " - Empty field");
        formValidator.validateEmailAddress(client.getEmail());

        errors = formValidator.getErrors();

        if (errors.isEmpty()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");
            logger.info("Editing Client with id= " + client.getId());
            simpleClientsService.updateClient(client);

            // redirect to GetClientsListServlet
            logger.info("Redirect to GetClientsController");
            modelAndView = new ModelAndView("redirect:/clients.htm");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.ID, client.getId());
            modelAndView.addObject(Constants.CLIENT, client);
            modelAndView.addObject(Constants.ERRORS, errors);
        }
        return modelAndView;
    }
}
