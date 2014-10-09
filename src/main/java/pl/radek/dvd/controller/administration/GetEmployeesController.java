package pl.radek.dvd.controller.administration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.service.employees.EmployeeFacade;

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

    @Autowired
    private EmployeeFacade employeeFacade;

    public void setEmployeeFacade(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String handleRequest(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        int noOfPages;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        listDataRequest = new ListDataRequest(null, null, paginationInfo);

        PaginatedList<EmployeeData> employeeDataPaginatedList = employeeFacade.getEmployees(listDataRequest);
        int noOfRecords = employeeDataPaginatedList.getNoOfRecords();
        List<EmployeeData> employeeDataList = employeeDataPaginatedList.getDataList();

        noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        logger.info(" !!!! EMPLOYEES !!!! ");
        logger.info(" !!!! NO OF RECORDS : " + noOfRecords);
        logger.info(" !!!! RECORDS PER PAGE : " + recordsPerPage);
        logger.info(" !!!! NO OF PAGES : " + noOfPages);

        // follow to employees.jsp
        logger.info("Follow to employees jsp");
        modelMap.addAttribute(Constants.CURRENTPAGE, page);
        modelMap.addAttribute(Constants.NO_OF_PAGES, noOfPages);
        modelMap.addAttribute(Constants.EMPLOYEELIST, employeeDataList);


        return "/administration/employees";
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String getCurrentPage(@RequestParam(value = Constants.CURRENTPAGE, required = false) String currentPage,
                                 ModelMap modelMap) throws Exception {

        ListDataRequest listDataRequest;

        // Pagination Info
        int page = 1;
        int recordsPerPage = 5;
        if (currentPage != null) {
            page = Integer.parseInt(currentPage);
        }
        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);

        listDataRequest = new ListDataRequest(null, null, paginationInfo);

        PaginatedList<EmployeeData> employeeDataPaginatedList = employeeFacade.getEmployees(listDataRequest);
        List<EmployeeData> employeeDataList = employeeDataPaginatedList.getDataList();

        // follow to employees_page.jsp
        logger.info("Follow to employees_page jsp");
        modelMap.addAttribute(Constants.EMPLOYEELIST, employeeDataList);


        return "/administration/employees_page";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST/*, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE*/)
    @ResponseBody
    public String deleteEmp(@RequestParam("id") int id) throws Exception {

        logger.info("Deleting employee with id=" + id);

        employeeFacade.deleteEmployee(id);

        logger.info("Employee with id = " + id + " successfully deleted");

        //  return "/administration/employees_page";
        return "deleted";
    }

    /*@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void deleteEmp(@PathVariable("id") int id, ModelMap modelMap) throws Exception {

        logger.info("Deleting employee with id=" + id);

        employeeFacade.deleteEmployee(id);

        logger.info("Employee successfully deleted");

        //  return "/administration/employees_page";
        //  return "deleted";
    }*/

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newEmp() throws Exception {


        return "/administration/employees_page";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editEmp() throws Exception {


        return "/administration/employees_page";
    }
}
