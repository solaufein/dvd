package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.logic.ClientsDAO;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ClientsServiceImpl implements ClientsService {

    private static Logger logger = Logger.getLogger(ClientsServiceImpl.class);

    @Autowired
    private ClientsDAO clientsDAO;

    public void setClientsDAO(ClientsDAO clientsDAO) {
        this.clientsDAO = clientsDAO;
    }

    @Override
    public PaginatedList<ClientData> getClients(final ListDataRequest request) {
        List<Client> clients = clientsDAO.getClients(request);
        int noOfRecords = clientsDAO.getNoOfRecords(request);
        //conversion
        List<ClientData> clientDataList = convertClientListToClientDataList(clients);

        PaginatedListImpl paginatedList = new PaginatedListImpl();
        paginatedList.setDataList(clientDataList);
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public List<ClientData> getClients() {
        List<Client> clients = clientsDAO.getClients();
        List<ClientData> clientDataList = convertClientListToClientDataList(clients);

        return clientDataList;
    }

    @Override
    public ClientData getClient(int id) {
        Client client = clientsDAO.getClient(id);
        ClientData clientData = convertClientToClientData(client);

        return clientData;
    }



    @Override
    public void deleteClient(int id) {
        clientsDAO.deleteClient(id);
    }

    @Override
    public void addClient(ClientData client) {
        Client c = convertClientDataToClient(client);
        clientsDAO.addClient(c);
    }

    @Override
    public void updateClient(ClientData client) {
        Client c = convertClientDataToClient(client);
        clientsDAO.updateClient(c);
    }

    private List<ClientData> convertClientListToClientDataList(List<Client> clientList) {
        List<ClientData> clientDataList = new LinkedList<ClientData>();
        for (Client c : clientList){
            ClientData clientData = convertClientToClientData(c);
            clientDataList.add(clientData);
        }

        return clientDataList;
    }

    private List<Client> convertClientDataListToClientList(List<ClientData> clientDataList) {
        List<Client> clientList = new LinkedList<Client>();
        for (ClientData cd : clientDataList){
            Client client = convertClientDataToClient(cd);
            clientList.add(client);
        }

        return clientList;
    }

    private ClientData convertClientToClientData(Client client) {
        ClientData clientData = new ClientData();
        clientData.setId(client.getId());
        clientData.setFirstName(client.getFirstName());
        clientData.setLastName(client.getLastName());
        clientData.setPesel(client.getPesel());
        clientData.setCity(client.getCity());
        clientData.setStreet(client.getStreet());
        clientData.setPhoneNumber(client.getPhoneNumber());
        clientData.setEmail(client.getEmail());

        return clientData;
    }

    private Client convertClientDataToClient(ClientData clientData) {
        Client client = new Client();
        client.setId(clientData.getId());
        client.setFirstName(clientData.getFirstName());
        client.setLastName(clientData.getLastName());
        client.setPesel(clientData.getPesel());
        client.setCity(clientData.getCity());
        client.setStreet(clientData.getStreet());
        client.setPhoneNumber(clientData.getPhoneNumber());
        client.setEmail(clientData.getEmail());

        return client;
    }
}
