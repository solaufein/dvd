package pl.radek.dvd.service.clients;

import pl.radek.dvd.dto.clients.ClientData;
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
public interface ClientsService {
    public List<ClientData> getClients();
    public ClientData getClient(int id);
    public PaginatedList<ClientData> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(ClientData client);
    public void updateClient(ClientData client);
}
