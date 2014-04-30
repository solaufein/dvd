package pl.radek.dvd.logic;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.model.Employee;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 16:20
 */
public interface EmployeeDAO {
    public List<Employee> getEmployees();
    public List<Employee> getEmployees(ListDataRequest listDataRequest);
    public Employee getEmployee(int id);
    public Employee getEmployee(String name);
    public void deleteEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
