package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.ListDataRequest;
import pl.radek.dvd.service.ClientsListService;
import pl.radek.dvd.service.ClientsService;
import pl.radek.dvd.service.SimpleClientsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 17.01.14
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/clients.htm")
public class GetClientsController {

    private static Logger logger = Logger.getLogger(GetClientsController.class);

    @Autowired
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    /*@Autowired
    private ClientsListService clientsListService;

    public void setClientsListService(ClientsListService clientsListService) {
        this.clientsListService = clientsListService;
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage) throws Exception {

        List<Client> clientList;
        int page = 1;
        int recordsPerPage = 5;

        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }

        int noOfRecords = simpleClientsService.getNoOfRecords();
     //   int noOfRecords = clientsListService.getClients(new ListDataRequest(null, null, null)).getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        logger.info("Getting client list from db");
        if ((field != null) && (order != null)) {
            logger.info("Field = " + field);
            logger.info("Order = " + order);
        }

        // sorting and paging logic method
        clientList = getClients(simpleClientsService, page, recordsPerPage, field, order);
    //    clientList = clientsListService.getClients(new ListDataRequest(null, null, null)).getDataList();

        ModelAndView modelAndView = new ModelAndView("/jsp/clients/clients_list.jsp");    // forward:  ?
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.CLIENTLIST, clientList);

        return modelAndView;
    }

    private List<Client> getClients(ClientsService clientsService, int page, int recordsPerPage, String field, String order) {
        List<Client> clientList;

        if (field == null && order == null) {
            clientList = clientsService.getClientsByPage((page - 1) * recordsPerPage, recordsPerPage);
        } else {
            clientList = clientsService.getClientsSortedAndPaged(field, order, (page - 1) * recordsPerPage, recordsPerPage);
        }
        return clientList;
    }
}
