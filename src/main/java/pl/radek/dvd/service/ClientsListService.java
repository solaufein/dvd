package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.ListDataRequest;

import java.util.List;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:37
 */

@Service
public class ClientsListService {
    private static Logger logger = Logger.getLogger(ClientsListService.class);

    @Autowired
    private ClientsMySQLDAO clientsMySQLDAO;

    public void setClientsMySQLDAO(ClientsMySQLDAO clientsMySQLDAO) {
        this.clientsMySQLDAO = clientsMySQLDAO;
    }

    public PaginatedList<Client> getClients(final ListDataRequest request) {
        List<Client> clients = clientsMySQLDAO.getClients(request);
        int noOfRecords = clientsMySQLDAO.getNoOfRecords(request);

        PaginatedListImpl paginatedList = new PaginatedListImpl();
        paginatedList.setDataList(clients);
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }
}
