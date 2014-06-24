package pl.radek.dvd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.*;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 13:54
 */

@Component
public class ClientFacadeImpl implements ClientFacade {

    private static Logger logger = Logger.getLogger(ClientFacadeImpl.class);

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public ClientData getClient(int id) {
        return clientsService.getClient(id);
    }

    @Override
    public PaginatedList<ClientData> getClients(ListDataRequest request) {
        return clientsService.getClients(request);
    }

    @Override
    public PaginatedList<ClientDetails> getClientDetails(ListDataRequest request, int clientId) {
        return clientDetailsService.getClientDetails(request, clientId);
    }

    @Override
    public ReceiptPdf getReceiptPdfInformations(int id) {
        return clientDetailsService.getReceiptPdfInformations(id);
    }

    @Override
    public void deleteClient(int id) {
        clientsService.deleteClient(id);
    }

    @Override
    public void addClient(ClientData client) {
        clientsService.addClient(client);
    }

    @Override
    public void updateClient(ClientData client) {
        clientsService.updateClient(client);
    }
}
