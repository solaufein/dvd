package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.model.Client;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 13:54
 */

@Component
public class ClientFacadeImpl implements ClientFacade<ClientData> {

    private static Logger logger = Logger.getLogger(ClientFacadeImpl.class);

    @Autowired
    private SimpleClientsService simpleClientsService;

    public void setSimpleClientsService(SimpleClientsService simpleClientsService) {
        this.simpleClientsService = simpleClientsService;
    }

    @Override
    public List<ClientData> getClients() {
        return simpleClientsService.getClients();
    }

    @Override
    public ClientData getClient(int id) {
        return simpleClientsService.getClient(id);
    }

    @Override
    public PaginatedList<ClientData> getClients(ListDataRequest request) {
        return simpleClientsService.getClients(request);
    }

    @Override
    public void deleteClient(int id) {
        simpleClientsService.deleteClient(id);
    }

    @Override
    public void addClient(ClientData client) {
        simpleClientsService.addClient(client);
    }

    @Override
    public void updateClient(ClientData client) {
        simpleClientsService.updateClient(client);
    }
}
