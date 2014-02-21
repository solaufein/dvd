package pl.radek.dvd.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.logic.FormValidator;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 18.01.14
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */
public class UpdateClientServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UpdateClientServlet.class);
    ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        FormValidator formValidator = new FormValidator();
        Map<String, String> errors;

        // get params
        String ide = req.getParameter(Constants.ID);
        String firstName = req.getParameter(Constants.FIRSTNAME);
        String lastName = req.getParameter(Constants.LASTNAME);
        String pesel = req.getParameter(Constants.PESEL);
        String city = req.getParameter(Constants.CITY);
        String street = req.getParameter(Constants.STREET);
        String phoneNumber = req.getParameter(Constants.PHONENUMBER);
        String email = req.getParameter(Constants.EMAIL);

        logger.info("Update - Get request parameters (fields)");

        // validate form fields
        logger.info("Validate form fields");
        formValidator.validateAlfabeticField(firstName, Constants.FIRSTNAME, " - Invalid firstname");
        formValidator.validateAlfabeticField(lastName, Constants.LASTNAME, " - Invalid lastname");
        formValidator.validatePesel(pesel);
        formValidator.validateAlfabeticField(city, Constants.CITY, " - Invalid city");
        formValidator.validateEmptyField(street, Constants.STREET, " - Invalid street");
        formValidator.validateEmptyField(phoneNumber, Constants.PHONENUMBER, " - Empty field");
        formValidator.validateEmailAddress(email);

        errors = formValidator.getErrors();

        int id = Integer.parseInt(ide);

        if (errors.isEmpty()) {
            // No errors, redirect to client list
            logger.info("No Errors spotted");

            ClientsMySQLDAO mySQLDAO = (ClientsMySQLDAO) context.getBean("clientDAO");

            logger.info("Editing Client with id= " + id);
            mySQLDAO.updateClient(firstName, lastName, pesel, city, street, phoneNumber, email, id);

            // redirect to GetClientsListServlet
            logger.info("Redirect to GetClientsListServlet");
            resp.sendRedirect("/dvd/clients");
        } else {
            // Put errors in request scope and forward back to JSP.
            logger.info("Errors spotted, pass errors through request scope and forward back to JSP");

            Client client = new Client(id,firstName,lastName,pesel,city,street,phoneNumber,email);

            req.setAttribute(Constants.CLIENT, client);
            req.setAttribute(Constants.ERRORS, errors);

            logger.info("forward to add_client.jsp");
            req.getRequestDispatcher("/jsp/clients/add_client.jsp").forward(req, resp);
        }

    }
}
