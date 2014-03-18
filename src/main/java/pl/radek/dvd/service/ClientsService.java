package pl.radek.dvd.service;

import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.dto.ListDataRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsService {
    public List<Client> getClients();
    public Client getClient(int id);
    public PaginatedList<Client> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(Client client);
    public void updateClient(Client client);
}
