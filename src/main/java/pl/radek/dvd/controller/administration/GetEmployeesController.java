package pl.radek.dvd.controller.administration;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.model.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-11
 * Time: 16:53
 */

@Controller
@RequestMapping("/emp/administration/employees")
public class GetEmployeesController {
    private static Logger logger = Logger.getLogger(GetEmployeesController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                ModelMap modelMap) throws Exception {


        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }


        // follow to movie_details.jsp
        logger.info("Follow to employees jsp");
        modelMap.addAttribute(Constants.CURRENTPAGE, page);


        return "/administration/employees";
    }
}
