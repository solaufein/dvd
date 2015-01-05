package pl.radek.dvd.controller.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.employees.EmployeeFacade;

@Controller
public class ChangePwController {
    private static Logger logger = Logger.getLogger(ChangePwController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String changePw(@RequestParam(value = "empId") String empId,
                           @RequestParam(value = "pw") String pw,
                           ModelMap modelMap) {

        // if link is active then save new password to db, redirect: Login.page
        // else go to remind view with msg

        try {
            employeeFacade.changePassword(empId, pw);
        } catch (EmployeeNotFoundException e) {
            modelMap.addAttribute("msg", "Employee not found. Link expired or changed.");
            return "/change";
        }

        return "redirect:/login?changed";
    }
}
