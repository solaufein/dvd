package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.clients.ClientFacade;

import javax.validation.Valid;

@Controller
@RequestMapping("/emp/clients/addclient")
public class AddClientController {

    private static Logger logger = Logger.getLogger(AddClientController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@ModelAttribute("client") @Valid ClientData client, BindingResult result) throws Exception {

        ModelAndView modelAndView;
        logger.info("AddClient - Get request parameters(fields)");

        if (!result.hasErrors()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");
            logger.info("Adding client to db");
            clientFacade.addClient(client);

            // redirect to GetClientsController
            logger.info("Redirect to GetClientsController");
            modelAndView = new ModelAndView("redirect:/emp/clients/clientslist");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            modelAndView = new ModelAndView("/clients/add_client");
            modelAndView.addObject(Constants.ID, client.getId());
            modelAndView.addObject(Constants.CLIENT, client);
        }
        return modelAndView;
    }
}
