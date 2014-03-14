package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;

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
    public List<Client> getClients() {
        return clientsMySQLDAO.getClients();
    }

    @Override
    public List getClientsByPage(int offset, int noOfRecords) {
        return clientsMySQLDAO.getClientsByPage(offset, noOfRecords);
    }

    @Override
    public List<Client> getClientsSortedAndPaged(String field, String order, int offset, int noOfRecords) {
        return clientsMySQLDAO.getClientsSortedAndPaged(field, order, offset, noOfRecords);
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
    public void addClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email) {
        clientsMySQLDAO.addClient(first_name, last_name, pesel, city, street, phone_number, email);
    }

    @Override
    public void addClient(Client client) {
        clientsMySQLDAO.addClient(client);
    }

    @Override
    public void updateClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email, int id) {
        clientsMySQLDAO.updateClient(first_name, last_name, pesel, city, street, phone_number, email, id);
    }

    @Override
    public void updateClient(Client client) {
        clientsMySQLDAO.updateClient(client);
    }

    @Override
    public int getNoOfRecords() {
        return clientsMySQLDAO.getNoOfRecords();
    }
}
