package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.logic.FormValidator;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.SimpleClientsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(Constants.ID) String id,
                                      @RequestParam(Constants.FIRSTNAME) String firstName,
                                      @RequestParam(Constants.LASTNAME) String lastName,
                                      @RequestParam(Constants.PESEL) String pesel,
                                      @RequestParam(Constants.CITY) String city,
                                      @RequestParam(Constants.STREET) String street,
                                      @RequestParam(Constants.PHONENUMBER) String phoneNumber,
                                      @RequestParam(Constants.EMAIL) String email) throws Exception {

        FormValidator formValidator = new FormValidator();
        Map<String, String> errors;
        ModelAndView modelAndView;

        logger.info("AddClient - Get request parameters(fields)");

        // validate form fields
        logger.info("Validate form fields");
        formValidator.validateAlfabeticField(firstName, Constants.FIRSTNAME, " - Invalid Firstname");
        formValidator.validateAlfabeticField(lastName, Constants.LASTNAME, " - Invalid Lastname");
        formValidator.validatePesel(pesel);
        formValidator.validateAlfabeticField(city, Constants.CITY, " - Invalid City");
        formValidator.validateEmptyField(street, Constants.STREET, " - Invalid Street");
        formValidator.validateEmptyField(phoneNumber, Constants.PHONENUMBER, " - Empty field");
        formValidator.validateEmailAddress(email);

        errors = formValidator.getErrors();

        if (errors.isEmpty()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");
            logger.info("Adding client to db");
            simpleClientsService.addClient(firstName, lastName, pesel, city, street, phoneNumber, email);

            // redirect to GetClientsListServlet
            logger.info("Redirect to GetClientsController");
            modelAndView = new ModelAndView("redirect:/clients.htm");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.ID, id);
            modelAndView.addObject(Constants.ERRORS, errors);
        }
        return modelAndView;
    }
}
