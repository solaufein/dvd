package pl.radek.dvd.controller.clients;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.clients.ClientFacade;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-05-16
 * Time: 16:12
 */

@Controller
@RequestMapping("/emp/clients/clientdetails")
public class ClientDetailsController {

    private static Logger logger = Logger.getLogger(ClientDetailsController.class);

    @Autowired
    private ClientFacade clientFacade;

    public void setClientFacade(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView handleRequest(@RequestParam(value = Constants.ID, required = false) String id,
                                      @RequestParam(value = Constants.ORDER, required = false) String order,
                                      @RequestParam(value = Constants.FIELD, required = false) String field,
                                      @RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage) throws Exception {
        ModelAndView modelAndView;

        // get id from clients_list.jsp form
        logger.info("Getting details for client with id=" + id);

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);
        ListDataRequest listDataRequest = new ListDataRequest(null, null, paginationInfo);

        int ide = Integer.parseInt(id);

        ClientData client = clientFacade.getClient(ide);
        PaginatedList<ClientDetails> clientDetailsPaginatedList = clientFacade.getClientDetails(listDataRequest, ide);
        List<ClientDetails> clientDetailsList = clientDetailsPaginatedList.getDataList();
        int noOfRecords = clientDetailsPaginatedList.getNoOfRecords();
        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        // follow to client_details.jsp
        logger.info("Follow to client_details jsp");
        modelAndView = new ModelAndView("/clients/client_details");
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
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

