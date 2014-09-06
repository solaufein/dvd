package pl.radek.dvd.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Sola
 * Date: 2014-07-02
 * Time: 08:46
 */
public class LocationStatusBarInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = Logger.getLogger(LocationStatusBarInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {

            String viewName = modelAndView.getViewName();
            String requestURI = request.getRequestURI();
            String[] menuItem = viewName.split("/");

            modelAndView.addObject("springViewName", viewName);

            if (menuItem.length > 1) {
                modelAndView.addObject("menuItem", menuItem[1]);
            } else {
                modelAndView.addObject("menuItem", menuItem[0]);             // ok ?
            }

            /*for (String s : menuItem) {
                logger.debug("Menu Item = " + s);
            }*/
            //    logger.debug("Twoja bierzaca lokacja ViewName: " + viewName);
            //    logger.debug("Twoja bierzaca lokacja URI: " + requestURI);
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
