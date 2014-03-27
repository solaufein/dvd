package pl.radek.dvd.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.radek.dvd.dto.ClientData;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.service.ClientFacadeImpl;
import pl.radek.dvd.service.SimpleClientsService;

/**
 * User: Sola
 * Date: 2014-03-25
 * Time: 21:41
 */
public class AddClientControllerTest {

    /*@Mock
    private SimpleClientsService simpleClientsService;*/

    @Mock
    private ClientFacadeImpl clientFacade;

    @InjectMocks
    private AddClientController addClientController;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    //    addClientController = new AddClientController();
    }

    @AfterMethod
    public void tearDown() throws Exception {

    }

    @Test
    public void testHandleRequestNoErrors() throws Exception {
        final int id = 10;
        ClientData client = new ClientData(id, "rad", "wit", "22233344455", "Krak", "ul ladna", "555-222", "rad@rad.op.pl");

        BindingResult bindingResult = new BeanPropertyBindingResult(client, "client");

        ModelAndView modelAndView = addClientController.handleRequest(client, bindingResult);
        Assert.assertEquals("redirect:/clients.htm", modelAndView.getViewName());
    }

    @Test
    public void testHandleRequestHasErrors() throws Exception {
        final int id = 10;
        ClientData client = new ClientData(id, "rad", "wit", "22233344455", "Krak", "ul ladna", "555-222", "rad@rad.op.pl");

        BindingResult bindingResult = new BeanPropertyBindingResult(client, "client");
        bindingResult.addError(new ObjectError("client","jakis tam error"));

        ModelAndView modelAndView = addClientController.handleRequest(client, bindingResult);
        Assert.assertEquals("forward:/jsp/clients/add_client.jsp", modelAndView.getViewName());
        Assert.assertNotNull(modelAndView.getModel().get("client"));
        Assert.assertEquals(client, modelAndView.getModel().get("client"));
    }
}
