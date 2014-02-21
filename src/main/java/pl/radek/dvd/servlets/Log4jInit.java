package pl.radek.dvd.servlets;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 22.01.14
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class Log4jInit extends HttpServlet {

    public
    void init() {
        String prefix =  getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-init-file");
        // if the log4j-init-file is not set, then no point in trying
        if(file != null) {
            PropertyConfigurator.configure(prefix + file);
        }
    }

    public
    void doGet(HttpServletRequest req, HttpServletResponse res) {
    }
}