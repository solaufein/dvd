package pl.radek.dvd.logic;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getEmployees();
    public List<Employee> getEmployees(ListDataRequest listDataRequest);
    public Employee getEmployee(int id);
    public Employee getEmployee(String login);
    public Employee getEmployeeByEmpId(String empId);
    public void deleteEmployee(int id);
    public void addEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public void setPasswordChangeKey(String email, String code);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
