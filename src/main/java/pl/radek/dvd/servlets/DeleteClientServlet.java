package pl.radek.dvd.servlets;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import pl.radek.dvd.logic.ClientsMySQLDAO;
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
 * Date: 17.01.14
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */
public class DeleteClientServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(DeleteClientServlet.class);
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
        int id = Integer.parseInt(ide);
        logger.info("get id from clients_list.jsp form, id=" + ide);

        ClientsMySQLDAO mySQLDAO = (ClientsMySQLDAO) context.getBean("clientDAO");

        logger.info("Deleting client with id=" + ide);
        mySQLDAO.deleteClient(id);


        // redirect to GetClientsListServlet
        logger.info("Redirect to GetClientsListServlet");
        resp.sendRedirect("/dvd/clients");


    }
}
