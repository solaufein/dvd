package pl.radek.dvd.dto.roles;

import pl.radek.dvd.model.Employee;

import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-10-21
 * Time: 13:39
 */
public class RoleData {

    private int id;
    private String role;
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
