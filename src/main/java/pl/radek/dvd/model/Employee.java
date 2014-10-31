package pl.radek.dvd.model;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 14:10
 */

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "pw_change_key")
    private String pwChangeKey;

    @Column(name = "pw_change_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pwChangeDate;


    @ManyToMany(fetch=FetchType.LAZY)    // cascade = {CascadeType.ALL},fetch=FetchType.EAGER ?       // cascade={CascadeType.PERSIST, CascadeType.MERGE}
    @JoinTable(name = "employee_roles",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Roles> rolesSet = new HashSet<Roles>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")                          // cascade={CascadeType.PERSIST, CascadeType.MERGE}
    private Set<RentingRegistry> rentingRegistries = new HashSet<RentingRegistry>(0);

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.pwChangeKey = pwChangeKey;
        this.pwChangeDate = pwChangeDate;
    }

    public Employee(int id, String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.pwChangeKey = pwChangeKey;
        this.pwChangeDate = pwChangeDate;
    }

    public Employee(int id, String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate, Set<Roles> rolesSet, Set<RentingRegistry> rentingRegistries) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.pwChangeKey = pwChangeKey;
        this.pwChangeDate = pwChangeDate;
        this.rolesSet = rolesSet;
        this.rentingRegistries = rentingRegistries;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPwChangeKey() {
        return pwChangeKey;
    }

    public Date getPwChangeDate() {
        return pwChangeDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPwChangeKey(String pwChangeKey) {
        this.pwChangeKey = pwChangeKey;
    }

    public void setPwChangeDate(Date pwChangeDate) {
        this.pwChangeDate = pwChangeDate;
    }

    public Set<Roles> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<Roles> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public Set<RentingRegistry> getRentingRegistries() {
        return rentingRegistries;
    }

    public void setRentingRegistries(Set<RentingRegistry> rentingRegistries) {
        this.rentingRegistries = rentingRegistries;
    }
}

