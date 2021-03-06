package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.service.clients.ClientFacade;

@Controller
@RequestMapping("/emp/clients/delete")
@Secured("ROLE_ADMIN")
public class DeleteClientController {
    private static Logger logger = Logger.getLogger(DeleteClientController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView handleRequest(@RequestParam("id") int id) throws Exception {

        // get id from clients_list.jsp form
        logger.info("get id from clients_list.jsp form, id=" + id);
        logger.info("Deleting client with id=" + id);

        clientFacade.deleteClient(id);

        // redirect to GetClientsController
        ModelAndView modelAndView = new ModelAndView("redirect:/emp/clients/clientslist");

        return modelAndView;
    }
}
