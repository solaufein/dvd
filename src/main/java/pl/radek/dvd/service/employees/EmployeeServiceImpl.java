package pl.radek.dvd.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.logic.EmployeeDAO;
import pl.radek.dvd.model.Employee;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 17:13
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }

    @Override
    public List<Employee> getEmployees(ListDataRequest listDataRequest) {
        return employeeDAO.getEmployees(listDataRequest);
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

    @Override
    public Employee getEmployee(String name) {
        return employeeDAO.getEmployee(name);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteEmployee(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeDAO.updateEmployee(employee);
    }

    @Override
    public void setPasswordChangeKey(String email, String code) {
        employeeDAO.setPasswordChangeKey(email, code);
    }

    @Override
    public int getNoOfRecords() {
        return employeeDAO.getNoOfRecords();
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest) {
        return employeeDAO.getNoOfRecords(listDataRequest);
    }
}
