package pl.radek.dvd.service;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.model.Client;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 13:53
 */
public interface ClientFacade {
    public List<Client> getClients();
    public Client getClient(int id);
    public PaginatedList<Client> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(Client client);
    public void updateClient(Client client);
}
