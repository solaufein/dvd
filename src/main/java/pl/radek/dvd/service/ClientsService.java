package pl.radek.dvd.service;

import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.ListDataRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 07.03.14
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsService<T> {
    public List<T> getClients();
    public T getClient(int id);
    public PaginatedList<T> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(T client);
    public void updateClient(T client);
}
