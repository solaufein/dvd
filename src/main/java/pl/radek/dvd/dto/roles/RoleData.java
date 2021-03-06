package pl.radek.dvd.dto.roles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.radek.dvd.dto.employees.EmployeeData;
import pl.radek.dvd.model.Employee;

import java.util.HashSet;
import java.util.Set;

public class RoleData {

    private int id;
    private String role;

    @JsonIgnore // this is becouse lazyInitializationError was thrown - no session or session was closed, during ResponseBody json object in controller method
    private Set<Employee> employees = new HashSet<Employee>();

    public RoleData() {
    }

    public RoleData(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public RoleData(int id, String role, Set<Employee> employees) {
        this.id = id;
        this.role = role;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return role;
    }
}
