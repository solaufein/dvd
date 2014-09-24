package pl.radek.dvd.controller.login;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.service.employees.EmployeeFacade;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 13:35
 */

@Controller
public class RemindController {
    private static Logger logger = Logger.getLogger(RemindController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private JavaMailSender mailSender;

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public void setSecureRandom(SecureRandom secureRandom) {
        this.secureRandom = secureRandom;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/remind", method = RequestMethod.GET)
    public ModelAndView pwReminderForm(
            @RequestParam(value = "msg", required = false) String msg) {

        ModelAndView model = new ModelAndView("/remind");
        if (msg != null) {
            model.addObject("msg", "Recovery link has been sent!");
        }

        return model;

    }

    @RequestMapping(value = "/remind", method = RequestMethod.POST)
    public ModelAndView sendRecoveryLink(@RequestParam(value = Constants.EMAIL) String email) {

        ModelAndView model;

        // generate 25-26 alphanumerical code
        String kod = new BigInteger(130, secureRandom).toString(32);

        // save generated code to db (by given email)
        try {
            employeeFacade.setPasswordChangeKey(email, kod);
        } catch (EmployeeNotFoundException e) {
            model = new ModelAndView("/remind");    // forward to remind.jsp with msg: employee not found
            model.addObject("msg", e.getMessage());
            return model;
        }

        // send email with link " http://localhost:8080/dvd/remind/{kod} " to given email
        String subject = "Dvd Rentals - Request Changing Password";
        String link = "http://localhost:8080/dvd/remind/" + kod;
        StringBuilder message = new StringBuilder("Hello,");
        message.append("Recovery password link: ");
        message.append(link);

        // creates a simple e-mail object
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message.toString());

        // sends the e-mail
        mailSender.send(simpleMailMessage);

        model = new ModelAndView("/remind");    // forward to remind.jsp
        model.addObject("msg", "Recovery link has been sent!");

        return model;

    }

    @RequestMapping(value = " /remind/{empId}", method = RequestMethod.GET)
    public String checkLinkExp(@PathVariable String empId,
                               ModelMap modelMap) {
        // obsluga linka w zaleznosci: jesli przekroczony 3min czasu LUB key nie istnieje - to msg i nic
        // a jesli mozna zmienic, to widok do zmiany.

        boolean isActiveLink = employeeFacade.checkLinkExp(empId);

        if (isActiveLink) {
            // link active (exists AND time is up to 5min), forward to change password view form
            modelMap.addAttribute("empId", empId);
            return "/change";
        } else {
            // link expired or not exists, forward to /remind with aproprietate message
            modelMap.addAttribute("msg", "Your link has expired or don't exists!");
            return "/remind";
        }
    }
}
