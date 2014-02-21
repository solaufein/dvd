package pl.radek.dvd.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pl.radek.dvd.logic.ClientsMySQLDAO;
import pl.radek.dvd.model.Client;
import pl.radek.dvd.model.Constants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 21.01.14
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
public class AddEditControlServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AddEditControlServlet.class);
    ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // get id from clients_list.jsp form
        String ide = req.getParameter(Constants.ID);
        logger.info("get id from clients_list.jsp form, id=" + ide);

        if (ide.equals("new")) {          // new client
            logger.info("id equals: new (new client)");
            req.setAttribute(Constants.ID, ide);
            req.getRequestDispatcher("/jsp/clients/add_client.jsp").forward(req, resp);
        } else {                         // edit client with given id
            logger.info("id equals: " + ide + "(edit client)");
            ClientsMySQLDAO mySQLDAO = (ClientsMySQLDAO) context.getBean("clientDAO");
            Client client = mySQLDAO.getClient(Integer.parseInt(ide));

            req.setAttribute(Constants.CLIENT, client);

            // follow to add_client.jsp
            req.getRequestDispatcher("/jsp/clients/add_client.jsp").forward(req, resp);
        }
    }
}
