package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.FiltreClientForm;
import pl.radek.dvd.model.*;
import pl.radek.dvd.service.clients.ClientFacade;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/emp/clients/clientslist")
public class GetClientsController {

    private static Logger logger = Logger.getLogger(GetClientsController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @InitBinder     /* Converts empty strings into null when a form is submitted */
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
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
            filterInfoList = getFilterInfos(client);
            ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);

            PaginatedList<ClientData> clientPaginatedList = clientFacade.getClients(listDataRequest);
            int noOfRecords = clientPaginatedList.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            clientList = clientPaginatedList.getDataList();

        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            ListDataRequest listDataRequest = new ListDataRequest(sortInfo, filterInfoList, paginationInfo);

            PaginatedList<ClientData> clientPaginatedList = clientFacade.getClients(listDataRequest);
            int noOfRecords = clientPaginatedList.getNoOfRecords();
            noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

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

    private List<FilterInfo> getFilterInfos(FiltreClientForm client) {
        List<FilterInfo> filterInfoList;
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
        return filterInfoList;
    }
}
