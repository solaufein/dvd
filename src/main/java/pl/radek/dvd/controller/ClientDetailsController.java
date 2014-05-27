package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.dto.ClientDetails;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.ClientFacade;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-05-16
 * Time: 16:12
 */

@Controller
@RequestMapping("/emp/clientdetails.htm")
public class ClientDetailsController {

    private static Logger logger = Logger.getLogger(ClientDetailsController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

        @RequestMapping(method= RequestMethod.GET)
        public ModelAndView handleRequest(@RequestParam(value = Constants.ID, required = false) String id) throws Exception {
            ModelAndView modelAndView;

            // get id from clients_list.jsp form
            logger.info("Getting details for client with id=" + id);

            int ide = Integer.parseInt(id);

            ClientData client = clientFacade.getClient(ide);
            List<ClientDetails> clientDetailsList = clientFacade.getClientDetails(ide);

            // follow to client_details.jsp
            logger.info("Follow to client_details jsp");
            modelAndView = new ModelAndView("/clients/client_details");
            modelAndView.addObject(Constants.CLIENT, client);
            modelAndView.addObject(Constants.CLIENTDETAILS, clientDetailsList);

            return modelAndView;
        }

   /* @RequestMapping(method= RequestMethod.GET)
    public ModelAndView changeLocale() throws Exception {
        ModelAndView modelAndView;

        modelAndView = new ModelAndView("/clients/client_details");

        return modelAndView;
    }*/
}

