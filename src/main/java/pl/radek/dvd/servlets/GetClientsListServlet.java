package pl.radek.dvd.servlets;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;

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

@org.springframework.stereotype.Controller
public class GetClientsListServlet implements Controller {

    private static Logger logger = Logger.getLogger(GetClientsListServlet.class);

    @Autowired
    private ClientsMySQLDAO clientsMySQLDAO;

    public void setClientsMySQLDAO(ClientsMySQLDAO clientsMySQLDAO) {
        this.clientsMySQLDAO = clientsMySQLDAO;
    }


    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  //      resp.setContentType("text/html");
        List<Client> clientList;

        int page = 1;
        int recordsPerPage = 5;

        String currPage = req.getParameter(Constants.CURRENTPAGE);
        if (currPage != null) {
            page = Integer.parseInt(currPage);
        }

        int noOfRecords = clientsMySQLDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        String field = req.getParameter(Constants.FIELD);
        String order = req.getParameter(Constants.ORDER);

        logger.info("Getting client list from db");
        if ((field != null) && (order != null)) {
            logger.info("Field = " + field);
            logger.info("Order = " + order);
        }

        // sorting and paging logic method
        clientList = getClients(clientsMySQLDAO, page, recordsPerPage, field, order);

  //      req.getRequestDispatcher("/jsp/clients/clients_list.jsp").forward(req, resp);

        ModelAndView modelAndView = new ModelAndView("/jsp/clients/clients_list.jsp");    // forward:  ?
        modelAndView.addObject(Constants.NO_OF_PAGES, noOfPages);
        modelAndView.addObject(Constants.CURRENTPAGE, page);
        modelAndView.addObject(Constants.FIELD, field);
        modelAndView.addObject(Constants.ORDER, order);
        modelAndView.addObject(Constants.CLIENTLIST, clientList);

        return modelAndView;
    }

    private List<Client> getClients(ClientsMySQLDAO mySQLDAO, int page, int recordsPerPage, String field, String order) {
        List<Client> clientList;
        if (Constants.ASC.equals(order) && Constants.FIRSTNAME.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.FIRSTNAME, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.FIRSTNAME.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.FIRSTNAME, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.LASTNAME.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.LASTNAME, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.LASTNAME.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.LASTNAME, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.PESEL.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.PESEL, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.PESEL.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.PESEL, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.CITY.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.CITY, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.CITY.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.CITY, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.STREET.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.STREET, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.STREET.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.STREET, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.PHONENUMBER.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.PHONENUMBER, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.PHONENUMBER.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.PHONENUMBER, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.ASC.equals(order) && Constants.EMAIL.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.EMAIL, Constants.ASC, (page - 1) * recordsPerPage, recordsPerPage);
        } else if (Constants.DESC.equals(order) && Constants.EMAIL.equals(field)) {
            clientList = mySQLDAO.getClientsSortedAndPaged(Constants.EMAIL, Constants.DESC, (page - 1) * recordsPerPage, recordsPerPage);
        } else {
            clientList = mySQLDAO.getClientsByPage((page - 1) * recordsPerPage, recordsPerPage);
        }
        return clientList;
    }
}
