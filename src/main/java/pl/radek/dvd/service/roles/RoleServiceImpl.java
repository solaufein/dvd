package pl.radek.dvd.service.roles;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.logic.RoleDAO;
import pl.radek.dvd.model.Employee;
import pl.radek.dvd.model.Roles;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-10-21
 * Time: 13:35
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static Logger logger = Logger.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDAO roleDAO;

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<RoleData> getRoles() {

        List<Roles> roles = roleDAO.getRoles();
        List<RoleData> roleDataList = convertRolesListToRoleDataList(roles);

        return roleDataList;
    }

    @Override
    public RoleData getRole(int id) {
        Roles role = roleDAO.getRole(id);
        RoleData roleData = convertRolesToRoleData(role);
        return roleData;
    }

    @Override
    public Roles getRoleEntity(int id) {
        Roles role = roleDAO.getRole(id);
        return role;
    }

    @Override
    public void deleteRole(int id) {
        roleDAO.deleteRole(id);
    }

    private List<RoleData> convertRolesListToRoleDataList(List<Roles> roles) {
        List<RoleData> roleDataList = new LinkedList<RoleData>();

        for (Roles role : roles) {

            RoleData roleData = convertRolesToRoleData(role);

            roleDataList.add(roleData);
        }

        return roleDataList;
    }

    private RoleData convertRolesToRoleData(Roles role) {
        RoleData roleData = new RoleData();
        roleData.setId(role.getId());
        roleData.setRole(role.getRole());

        roleData.setEmployees(role.getEmployees());

        return roleData;
    }

    private Set<EmployeeData> convertEmployeeSetToEmployeeDataSet(Set<Employee> employees) {
        Set<EmployeeData> employeeDataSet = new HashSet<EmployeeData>();
        for (Employee employee : employees) {
            EmployeeData employeeData = convertEmployeeToEmployeeData(employee);
            employeeDataSet.add(employeeData);
        }

        return employeeDataSet;
    }

    private Set<Employee> convertEmployeeDataSetToEmployeeSet(Set<EmployeeData> employeeDataSet) {
        Set<Employee> employeeSet = new HashSet<Employee>();
        for (EmployeeData employeeData : employeeDataSet) {
            Employee e = convertEmployeeDataToEmployee(employeeData);
            employeeSet.add(e);
        }
        return employeeSet;
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

    private Roles convertRoleDataToRoles(RoleData roleData) {
        Roles roles = new Roles();
        roles.setId(roleData.getId());
        roles.setRole(roleData.getRole());
        roles.setEmployees(roleData.getEmployees());

        return roles;
    }
}
