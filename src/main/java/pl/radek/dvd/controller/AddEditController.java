package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.ClientFacade;
import pl.radek.dvd.service.ClientFacadeImpl;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:16
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/emp/controller.htm")
@Secured("ROLE_ADMIN")
public class AddEditController {
    private static Logger logger = Logger.getLogger(AddEditController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
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
            ClientData client = new ClientData();
            client.setId(-1);
            modelAndView.addObject(Constants.CLIENT, client);
       //     modelAndView.addObject(Constants.ID, id);

        } else {                         // edit client with given id
            logger.info("id equals: " + id + "(edit client)");
            ClientData client = clientFacade.getClient(Integer.parseInt(id));

            // follow to add_client.jsp
            modelAndView = new ModelAndView("forward:/jsp/clients/add_client.jsp");
            modelAndView.addObject(Constants.CLIENT, client);
        }
        return modelAndView;
    }
}
