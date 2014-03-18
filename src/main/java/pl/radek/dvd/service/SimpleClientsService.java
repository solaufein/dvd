package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginatedListImpl;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.dto.ListDataRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SimpleClientsService implements ClientsService {

    private static Logger logger = Logger.getLogger(SimpleClientsService.class);

    @Autowired
    private ClientsMySQLDAO clientsMySQLDAO;

    public void setClientsMySQLDAO(ClientsMySQLDAO clientsMySQLDAO) {
        this.clientsMySQLDAO = clientsMySQLDAO;
    }

    @Override
    public PaginatedList<Client> getClients(final ListDataRequest request) {
        List<Client> clients = clientsMySQLDAO.getClients(request);
        int noOfRecords = clientsMySQLDAO.getNoOfRecords(request);

        PaginatedListImpl paginatedList = new PaginatedListImpl();
        paginatedList.setDataList(clients);
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public List<Client> getClients() {
        return clientsMySQLDAO.getClients();
    }

    @Override
    public Client getClient(int id) {
        return clientsMySQLDAO.getClient(id);
    }

    @Override
    public void deleteClient(int id) {
        clientsMySQLDAO.deleteClient(id);
    }

    @Override
    public void addClient(Client client) {
        clientsMySQLDAO.addClient(client);
    }

    @Override
    public void updateClient(Client client) {
        clientsMySQLDAO.updateClient(client);
    }
}
