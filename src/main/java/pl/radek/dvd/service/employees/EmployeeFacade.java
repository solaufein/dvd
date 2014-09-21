package pl.radek.dvd.service.employees;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.model.Employee;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 17:13
 */
public interface EmployeeFacade {
    public List<Employee> getEmployees();
    public List<Employee> getEmployees(ListDataRequest listDataRequest);
    public Employee getEmployee(int id);
    public Employee getEmployee(String name);
    public void deleteEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void passwordChangeKey(String email, String code);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
