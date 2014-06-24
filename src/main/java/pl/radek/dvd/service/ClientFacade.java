package pl.radek.dvd.service;

import pl.radek.dvd.dto.*;
import pl.radek.dvd.model.Client;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 13:53
 */
public interface ClientFacade {
    public ClientData getClient(int id);
    public PaginatedList<ClientData> getClients(final ListDataRequest request);
    public PaginatedList<ClientDetails> getClientDetails(final ListDataRequest request, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
    public void deleteClient(int id);
    public void addClient(ClientData client);
    public void updateClient(ClientData client);
}
