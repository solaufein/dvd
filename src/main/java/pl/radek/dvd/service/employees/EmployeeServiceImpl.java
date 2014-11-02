package pl.radek.dvd.service.employees;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.dto.employees.PaginatedListEmployee;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.exceptions.employee.EmployeeNotFoundException;
import pl.radek.dvd.logic.EmployeeDAO;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.Roles;
import pl.radek.dvd.utils.Encryption;

import java.util.*;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 17:13
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

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
    public PaginatedList<EmployeeData> getEmployees(ListDataRequest listDataRequest) {
        List<Employee> employeeList = employeeDAO.getEmployees(listDataRequest);
        int noOfRecords = employeeDAO.getNoOfRecords();

        PaginatedListEmployee paginatedList = new PaginatedListEmployee();
        paginatedList.setEmployeeDataList(convertEmployeeListToEmployeeDataList(employeeList));
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public EmployeeData getEmployeeData(int id) {
        Employee employee = employeeDAO.getEmployee(id);
        EmployeeData employeeData = convertEmployeeToEmployeeData(employee);

        return employeeData;
    }

    @Override
    public EmployeeData getEmployeeJsonData(int id) {
        Employee employee = employeeDAO.getEmployee(id);
        EmployeeData employeeData = new EmployeeData();
        employeeData.setId(employee.getId());
        employeeData.setFirstName(employee.getFirstName());
        employeeData.setLastName(employee.getLastName());
        employeeData.setEmail(employee.getEmail());
        employeeData.setPhoneNumber(employee.getPhoneNumber());
        Set<Roles> rolesSet = employee.getRolesSet();
        employeeData.setRolesSet(convertRolesSetToRoleDataSet(rolesSet));

        return employeeData;
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
    public void addEmployee(EmployeeData employee) {
        Employee employeeObj = convertEmployeeDataToEmployee(employee);
        String password = employeeObj.getPassword();
        employeeObj.setPassword(Encryption.encrypt(password));             // set MD5 password
        employeeDAO.addEmployee(employeeObj);
    }

    @Override
    public void updateEmployee(EmployeeData employee) {
        Employee employeeObj = convertEmployeeDataToEmployee(employee);
        employeeDAO.updateEmployee(employeeObj);
    }

    @Override
    public void setPasswordChangeKey(String email, String code) throws EmployeeNotFoundException {
        Employee employee = employeeDAO.getEmployee(email);

        if (employee != null) {
            Date date = new Date();
            employee.setPwChangeKey(code);
            employee.setPwChangeDate(date);

            employeeDAO.updateEmployee(employee);
        } else {
            throw new EmployeeNotFoundException("Employee with given email: " + email + " not found!");
        }

        //   employeeDAO.setPasswordChangeKey(email, code);
    }

    @Override
    public void changePassword(String empId, String pw) throws EmployeeNotFoundException {
        Employee employeeByEmpId = employeeDAO.getEmployeeByEmpId(empId);

        if (employeeByEmpId != null) {        // this empId exists
            employeeByEmpId.setPassword(Encryption.encrypt(pw));       // encrypt password with md5
            employeeDAO.updateEmployee(employeeByEmpId);
        } else {
            throw new EmployeeNotFoundException("Employee with given changePwKey: " + empId + " not found!");
        }
    }

    @Override
    public boolean checkLinkExp(String empId) {
        Employee employeeByEmpId = employeeDAO.getEmployeeByEmpId(empId);


        if (employeeByEmpId != null) {        // this empId exists
            //check if previous date - current date < 5min
            Date pwChangeDate = employeeByEmpId.getPwChangeDate();
            Date currentDate = new Date();

            long diff = currentDate.getTime() - pwChangeDate.getTime();
            long diffMinutes = diff / (60 * 1000) % 60;

            if (diffMinutes < 3) {      //  if diffrence in minutes was less than 3min its ok
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    @Override
    public int getNoOfRecords() {
        return employeeDAO.getNoOfRecords();
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest) {
        return employeeDAO.getNoOfRecords(listDataRequest);
    }

    private List<EmployeeData> convertEmployeeListToEmployeeDataList(List<Employee> employees) {
        List<EmployeeData> list = new LinkedList<EmployeeData>();
        for (Employee employee : employees) {
            EmployeeData employeeData = convertEmployeeToEmployeeData(employee);
            list.add(employeeData);
        }

        return list;
    }

    private EmployeeData convertEmployeeToEmployeeData(Employee employee) {
        EmployeeData employeeData = new EmployeeData();

        employeeData.setId(employee.getId());
        employeeData.setFirstName(employee.getFirstName());
        employeeData.setLastName(employee.getLastName());
        employeeData.setEmail(employee.getEmail());
        employeeData.setPassword(employee.getPassword());
        employeeData.setPhoneNumber(employee.getPhoneNumber());
        employeeData.setPwChangeDate(employee.getPwChangeDate());
        employeeData.setPwChangeKey(employee.getPwChangeKey());
        employeeData.setRentingRegistries(employee.getRentingRegistries());
        employeeData.setRolesSet(convertRolesSetToRoleDataSet(employee.getRolesSet()));

        return employeeData;
    }

    private Employee convertEmployeeDataToEmployee(EmployeeData employeeData) {
        Employee employee = new Employee();

        employee.setId(employeeData.getId());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employeeData.getEmail());
        employee.setPassword(employeeData.getPassword());
        employee.setPhoneNumber(employeeData.getPhoneNumber());
        employee.setPwChangeDate(employeeData.getPwChangeDate());
        employee.setPwChangeKey(employeeData.getPwChangeKey());
        employee.setRentingRegistries(employeeData.getRentingRegistries());
        employee.setRolesSet(convertRoleDataSetToRolesSet(employeeData.getRolesSet()));

        return employee;
    }

    private Set<RoleData> convertRolesSetToRoleDataSet(Set<Roles> rolesSet) {
        Set<RoleData> roleDataSet = new HashSet<RoleData>();
        for (Roles roles : rolesSet) {
            RoleData roleData = convertRolesToRoleData(roles);
            roleDataSet.add(roleData);
        }
        return roleDataSet;
    }

    private Set<Roles> convertRoleDataSetToRolesSet(Set<RoleData> roleDataSet) {
        Set<Roles> rolesSet = new HashSet<Roles>();
        for (RoleData roleData : roleDataSet) {
            Roles roles = convertRoleDataToRoles(roleData);
            rolesSet.add(roles);
        }
        return rolesSet;
    }

    private RoleData convertRolesToRoleData(Roles role) {
        RoleData roleData = new RoleData();
        roleData.setId(role.getId());
        roleData.setRole(role.getRole());
        roleData.setEmployees(role.getEmployees());

        return roleData;
    }

    private Roles convertRoleDataToRoles(RoleData roleData) {
        Roles roles = new Roles();
        roles.setId(roleData.getId());
        roles.setRole(roleData.getRole());
        roles.setEmployees(roleData.getEmployees());

        return roles;
    }
}
