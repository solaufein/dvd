package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.ClientFacade;

/**
 * User: Sola
 * Date: 2014-06-24
 * Time: 17:19
 */

@Controller
@RequestMapping("/emp/printreceipt.htm")
public class PrintReceiptController {
    private static Logger logger = Logger.getLogger(PrintReceiptController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam(value = "registryid", required = false) String registryid,
                                      @RequestParam(value = "clientid", required = false) String clientid) throws Exception {
        ModelAndView modelAndView = null;
        logger.info("Getting PDF details for client with id=" + clientid + " and registry_id=" + registryid);

        //todo: tutaj zrobic pdf-a

        // follow to pdf view
        logger.info("Follow to pdf view");
        modelAndView = new ModelAndView("/clients/client_details");

        return modelAndView;
    }
}
