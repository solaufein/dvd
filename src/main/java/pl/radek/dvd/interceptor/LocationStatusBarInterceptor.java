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
            modelAndView.addObject("springViewName", modelAndView.getViewName());
            logger.debug("Twoja bierzaca lokacja ViewName: " + modelAndView.getViewName());
            logger.debug("Twoja bierzaca lokacja URI: " + request.getRequestURI());
        }
        super.postHandle(request, response, handler, modelAndView);
    }
}
