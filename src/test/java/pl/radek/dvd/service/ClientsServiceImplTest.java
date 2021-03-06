package pl.radek.dvd.service;


import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.radek.dvd.dto.clients.ClientData;
import pl.radek.dvd.logic.clients.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.service.clients.ClientsServiceImpl;

public class ClientsServiceImplTest {

    @Mock
    private ClientsMySQLDAO clientsMySQLDAO;

    @InjectMocks
    private ClientsServiceImpl clientsServiceImpl;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        //     clientsServiceImpl = new ClientsServiceImpl();
        //     clientsServiceImpl.setClientsMySQLDAO(clientsMySQLDAO);           // ok ?
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testDeleteClient() throws Exception {

    }

    @Test
    public void testAddClient() throws Exception {
        final int id = 10;
        ClientData client = new ClientData(id, "rad", "wit", "22233344455", "Krak", "ul ladna", "555-222", "rad@rad.op.pl");

        final ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        clientsServiceImpl.addClient(client);
        Mockito.verify(clientsMySQLDAO).addClient(clientCaptor.capture());
        Assert.assertEquals(clientCaptor.getValue().getFirstName(), client.getFirstName());
        Assert.assertEquals(clientCaptor.getValue().getLastName(), client.getLastName());

       /* Mockito.when(clientsMySQLDAO.getClient(id)).thenReturn(client);
        final Client c = clientsServiceImpl.getClient(id);*/

      /*
        clientsServiceImpl.addClient(client);
        Mockito.verify(clientsMySQLDAO, Mockito.times(2)).addClient(client);*/
    }

    @Test
    public void testUpdateClient() throws Exception {

    }

    public Client createClient() {
        return new Client();
    }
}


