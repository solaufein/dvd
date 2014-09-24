package pl.radek.dvd.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.model.Employee;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 17:15
 */

@Component
public class EmployeeFacadeImpl implements EmployeeFacade {

    @Autowired
    EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @Override
    public List<Employee> getEmployees(ListDataRequest listDataRequest) {
        return employeeService.getEmployees(listDataRequest);
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
    public void addEmployee(Employee employee) {
        employeeService.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
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
