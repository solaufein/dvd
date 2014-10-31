package pl.radek.dvd.service.employees;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.Roles;
import pl.radek.dvd.service.roles.RoleService;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 17:15
 */

@Component
public class EmployeeFacadeImpl implements EmployeeFacade {

    private static Logger logger = Logger.getLogger(EmployeeFacadeImpl.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<RoleData> getRoles() {
        return roleService.getRoles();
    }

    @Override
    public RoleData getRole(int id) {
        return roleService.getRole(id);
    }

    @Override
    public Roles getRoleEntity(int id) {
        return roleService.getRoleEntity(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @Override
    public PaginatedList<EmployeeData> getEmployees(ListDataRequest listDataRequest){
        return employeeService.getEmployees(listDataRequest);
    }

    @Override
    public EmployeeData getEmployeeData(int id) {
        return employeeService.getEmployeeData(id);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeService.getEmployee(id);
    }

    @Override
    public Employee getEmployee(String name) {
        return employeeService.getEmployee(name);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
    }

    @Override
    public void addEmployee(EmployeeData employee) {
        employeeService.addEmployee(employee);
    }

    @Override
    public void updateEmployee(EmployeeData employee) {
        employeeService.updateEmployee(employee);
    }

    @Override
    public void setPasswordChangeKey(String email, String code) throws EmployeeNotFoundException {
        employeeService.setPasswordChangeKey(email, code);
    }

    @Override
    public void changePassword(String empId, String pw) throws EmployeeNotFoundException {
        employeeService.changePassword(empId, pw);
    }

    @Override
    public boolean checkLinkExp(String empId) {
        return employeeService.checkLinkExp(empId);
    }

    @Override
    public int getNoOfRecords() {
        return employeeService.getNoOfRecords();
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest) {
        return employeeService.getNoOfRecords(listDataRequest);
    }
}
