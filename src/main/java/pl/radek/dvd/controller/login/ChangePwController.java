package pl.radek.dvd.controller.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.radek.dvd.service.employees.EmployeeFacade;

/**
 * User: Sola
 * Date: 2014-09-23
 * Time: 18:38
 */

@Controller
public class ChangePwController {
    private static Logger logger = Logger.getLogger(ChangePwController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    /*@RequestMapping(value = " /remind/change/{empId}", method = RequestMethod.GET)
    public String changePwForm(@PathVariable String empId, ModelMap modelMap) {
        //todo: change password form - view


        return "/change";
    }*/

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String changePw(ModelMap modelMap) {
        //todo: if link is active then save new password to db, redirect: Login.page
        //todo: else go to remind view with msg

      //  employeeFacade.changePassword();

        return "redirect:/login?changed";
    }
}
