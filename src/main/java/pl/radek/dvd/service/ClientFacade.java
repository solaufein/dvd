package pl.radek.dvd.service;

import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.model.Client;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 13:53
 */
public interface ClientFacade<T> {
    public List<T> getClients();
    public T getClient(int id);
    public PaginatedList<T> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(T client);
    public void updateClient(T client);
}
