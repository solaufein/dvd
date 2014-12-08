package pl.radek.dvd.controller.raports;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/emp/raports")
public class GetRaportsController {
    private static Logger logger = Logger.getLogger(GetRaportsController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getRaportsView(ModelMap modelMap) throws Exception {

        return "/raports/raports_list";
    }
}
