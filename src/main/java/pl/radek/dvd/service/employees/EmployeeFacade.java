package pl.radek.dvd.service.employees;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.Roles;

import java.util.List;

public interface EmployeeFacade {
    public List<RoleData> getRoles();
    public RoleData getRole(int id);
    public Roles getRoleEntity(int id);

    public List<Employee> getEmployees();
    public PaginatedList<EmployeeData> getEmployees(ListDataRequest listDataRequest);
    public EmployeeData getEmployeeData(int id);
    public EmployeeData getEmployeeJsonData(int id);
    public Employee getEmployee(int id);
    public Employee getEmployee(String name);
    public void deleteEmployee(int id);
    public void addEmployee(EmployeeData employee);
    public void updateEmployee(EmployeeData employee);
    public void setPasswordChangeKey(String email, String code) throws EmployeeNotFoundException;
    public void changePassword(String empId, String pw) throws EmployeeNotFoundException;
    public boolean checkLinkExp(String empId);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
