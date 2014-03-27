package pl.radek.dvd.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.model.*;
import pl.radek.dvd.service.ClientFacadeImpl;
import pl.radek.dvd.service.SimpleClientsService;

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
    private ClientFacadeImpl clientFacade;

    public void setClientFacade(ClientFacadeImpl clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage) throws Exception {

        List<ClientData> clientList;
        SortInfo sortInfo = null;
        FilterInfo filterInfo = null;
        int page = 1;
        int recordsPerPage = 5;

        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }

        if ((field != null) && (order != null)) {
            logger.info("Field = " + field);
            logger.info("Order = " + order);
            boolean isAscOrder;
            if (order.equals(Constants.ASC)) {
                isAscOrder = true;
            } else {
                isAscOrder = false;
            }

            sortInfo = new SortInfo(field, isAscOrder);
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);
        ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfo, paginationInfo);

        PaginatedList<ClientData> clientPaginatedList = clientFacade.getClients(listDataRequest);
        int noOfRecords = clientPaginatedList.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // sorting and paging logic method
        clientList = clientPaginatedList.getDataList();

        ModelAndView modelAndView = new ModelAndView("/jsp/clients/clients_list.jsp");    // forward:  ?
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.CLIENTLIST, clientList);

        return modelAndView;
    }
}
