package pl.radek.dvd.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

public class Config implements ServletContextListener {

    /* Can inject only beans that are in applicationContext. */

    public void contextInitialized(ServletContextEvent event) {

        //    event.getServletContext().setAttribute("menu", item);

        // Do stuff during webapp's startup.
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }

}
