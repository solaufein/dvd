package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.model.*;
import pl.radek.dvd.service.ClientFacade;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 17.01.14
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/emp/clients/clientslist.htm")
public class GetClientsController {

    private static Logger logger = Logger.getLogger(GetClientsController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                      @ModelAttribute("client") @Valid FiltreClientForm client, BindingResult result) throws Exception {

        ModelAndView modelAndView;
        List<ClientData> clientList;
        SortInfo sortInfo = null;
        List<FilterInfo> filterInfoList = null;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        // Sort Info
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

        if (!result.hasErrors()) {
            // No errors, redirect to client list
            logger.info("No errors spotted");

            // Filter Info
            String firstName = client.getFirstName();
            String lastName = client.getLastName();
            String pesel = client.getPesel();

            filterInfoList = new ArrayList<FilterInfo>();
            if (firstName != null && !firstName.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.FIRSTNAME, firstName));
            }
            if (lastName != null && !lastName.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.LASTNAME, lastName));
            }
            if (pesel != null && !pesel.equals("")) {
                filterInfoList.add(new FilterInfo(Constants.PESEL, pesel));
            }

            ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);

            PaginatedList<ClientData> clientPaginatedList = clientFacade.getClients(listDataRequest);
            int noOfRecords = clientPaginatedList.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
            logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
            logger.info(" !!!! NO OF PAGES : " + noOfPages);
            // sorting and paging logic method
            clientList = clientPaginatedList.getDataList();

        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);

            PaginatedList<ClientData> clientPaginatedList = clientFacade.getClients(listDataRequest);
            int noOfRecords = clientPaginatedList.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            // sorting and paging logic method
            clientList = clientPaginatedList.getDataList();
        }

        modelAndView = new ModelAndView("/clients/clients_list");
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.CLIENTLIST, clientList);
        modelAndView.addObject(Constants.CLIENT, client);

        return modelAndView;
    }
}
