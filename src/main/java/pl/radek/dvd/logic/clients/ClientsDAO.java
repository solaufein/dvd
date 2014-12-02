package pl.radek.dvd.logic.clients;

import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.dto.ListDataRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 05.02.14
 * Time: 13:18
 * To change this template use File | Settings | File Templates.
 */
public interface ClientsDAO {

    public List<Client> getClients();

    public List<Client> getClients(ListDataRequest listDataRequest);

    public Client getClient(int id);

    public void deleteClient(int id);

    public void addClient(Client client);

    public void updateClient(Client client);

    public int getNoOfRecords();

    public int getNoOfRecords(ListDataRequest listDataRequest);

    List<ClientRentDto> getClients(String pesel) throws ClientNotFoundException;

    ClientRentDto getClient(String pesel);

    List<Client> getClientByLastName(String lastName) throws ClientNotFoundException;
}
