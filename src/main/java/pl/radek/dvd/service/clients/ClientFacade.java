package pl.radek.dvd.service.clients;

import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.clients.ClientDetails;
import pl.radek.dvd.dto.clients.ReceiptPdf;

public interface ClientFacade {
    public ClientData getClient(int id);
    public PaginatedList<ClientData> getClients(final ListDataRequest request);
    public PaginatedList<ClientDetails> getClientDetails(final ListDataRequest request, int clientId);
    public ReceiptPdf getReceiptPdfInformations(int id);
    public void deleteClient(int id);
    public void addClient(ClientData client);
    public void updateClient(ClientData client);
}
