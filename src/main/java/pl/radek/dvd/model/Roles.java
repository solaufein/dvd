package pl.radek.dvd.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "role", nullable = false)
    private String role;

    @ManyToMany(mappedBy = "rolesSet",fetch=FetchType.LAZY)       // EAGER ?  // @JsonManagedReference // @JsonIgnore
    private Set<Employee> employees = new HashSet<Employee>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;

        Roles roles = (Roles) o;

        if (id != roles.id) return false;
        if (employees != null ? !employees.equals(roles.employees) : roles.employees != null) return false;
        if (role != null ? !role.equals(roles.role) : roles.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (employees != null ? employees.hashCode() : 0);
        return result;
    }
}
