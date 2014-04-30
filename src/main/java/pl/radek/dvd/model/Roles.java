package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-04-29
 * Time: 19:17
 */

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @ManyToMany(targetEntity=Employee.class, fetch = FetchType.LAZY, mappedBy = "rolesSet")
    private Set<Employee> employees = new HashSet<Employee>(0);

    public Roles() {
    }

    public Roles(String role) {
        this.role = role;
    }

    public Roles(int id, String role) {
        this.id = id;
        this.role = role;
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
}
