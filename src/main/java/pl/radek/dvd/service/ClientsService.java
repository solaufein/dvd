package pl.radek.dvd.service;

import pl.radek.dvd.model.Client;

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
    public List<Client> getClientsByPage(int offset, int noOfRecords);
    public List<Client> getClientsSortedAndPaged(String field, String order, int offset, int noOfRecords);
    public Client getClient(int id);
    public void deleteClient(int id);
    public void addClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email);
    public void updateClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email, int id);
    public int getNoOfRecords();
}
