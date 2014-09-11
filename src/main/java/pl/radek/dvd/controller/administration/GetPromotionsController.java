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
 * Time: 16:42
 */

@Controller
@RequestMapping("/emp/administration/promotions.htm")
public class GetPromotionsController {
    private static Logger logger = Logger.getLogger(GetPromotionsController.class);

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

        List<String> test = new LinkedList<String>();
        test.add("ananas");
        test.add("jablko");
        test.add("cytryna");
        test.add("groch");

        // follow to movie_details.jsp
        logger.info("Follow to promotions jsp");
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute("olek", "hello olek");
        modelMap.addAttribute("owoce", test);

        return "/administration/promotions";
    }
}
