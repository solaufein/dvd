package pl.radek.dvd.service.clients;

import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.model.Client;

import java.util.List;

public interface ClientsService {
    public List<ClientData> getClients();
    public ClientData getClient(int id);
    public PaginatedList<ClientData> getClients(final ListDataRequest request);
    public void deleteClient(int id);
    public void addClient(ClientData client);
    public void updateClient(ClientData client);

    List<ClientRentDto> getClients(String pesel) throws ClientNotFoundException;
    ClientRentDto getClient(String pesel);

    List<Client> getClientByLastName(String lastName) throws ClientNotFoundException;
}
