package pl.radek.dvd.controller.administration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.editor.employees.RoleCollectionsEditor;
import pl.radek.dvd.model.*;
import pl.radek.dvd.service.employees.EmployeeFacade;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/emp/administration/employees")
public class GetEmployeesController {
    private static Logger logger = Logger.getLogger(GetEmployeesController.class);

    @Autowired
    private EmployeeFacade employeeFacade;

    @ModelAttribute("allRoles")
    public List<RoleData> getAllRoles() {
        return employeeFacade.getRoles();
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "rolesSet", new RoleCollectionsEditor(Set.class, true, employeeFacade));
    }

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

        // add model for data binding in add/edit
        EmployeeData employeeData = new EmployeeData();

        // follow to employees.jsp
        logger.info("Follow to employees jsp");
        modelMap.addAttribute("emp", employeeData);
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
        } else {
            logger.info(" !!!! CurrentPage is NULL (0) ");
        }

        PaginationInfo paginationInfo = new PaginationInfo(page, recordsPerPage);
        listDataRequest = new ListDataRequest(null, null, paginationInfo);

        PaginatedList<EmployeeData> employeeDataPaginatedList = employeeFacade.getEmployees(listDataRequest);
        List<EmployeeData> employeeDataList = employeeDataPaginatedList.getDataList();

        // follow to employees_page.jsp
        logger.info("Follow to employees_page jsp");
        modelMap.addAttribute(Constants.EMPLOYEELIST, employeeDataList);

        logger.info(" !!!! PAGE : " + page);

        return "/administration/employees_page";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteEmp(@RequestParam("id") int id) throws Exception {

        logger.info("Deleting employee with id=" + id);

        employeeFacade.deleteEmployee(id);

        logger.info("Employee with id = " + id + " successfully deleted");

        return "Employee successfully deleted";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String newEmp(@ModelAttribute("emp") EmployeeData emp) throws Exception {
        logger.info("new Emp controller method start - adding new employee ");

        employeeFacade.addEmployee(emp);

        logger.info("new Emp controller method end - added employee ");

        return "Employee successfully added";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editEmp(@ModelAttribute("emp") EmployeeData emp) throws Exception {
        logger.info("edit Emp controller method start - editing employee ");

        employeeFacade.updateEmployee(emp);

        logger.info("edit Emp controller method end - edmployee edited ok ");

        return "Employee successfully edited and saved";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public EmployeeData getEmpById(@RequestParam("id") int id) throws Exception {
        logger.info("get Emp controller method start");

        EmployeeData employee = employeeFacade.getEmployeeJsonData(id);
        employee.setPwChangeDate(null);
        employee.setPwChangeKey(null);
        employee.setRentingRegistries(null);

        logger.info("get Emp controller method end");

        return employee;
    }
}
