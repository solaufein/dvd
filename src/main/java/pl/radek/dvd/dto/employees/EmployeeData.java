package pl.radek.dvd.dto.employees;

import pl.radek.dvd.dto.roles.RoleData;
import pl.radek.dvd.model.RentingRegistry;
import pl.radek.dvd.model.Roles;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-09-19
 * Time: 14:34
 */
public class EmployeeData {

    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String pwChangeKey;
    private Date pwChangeDate;
    private Set<RoleData> rolesSet = new HashSet<RoleData>();
    private Set<RentingRegistry> rentingRegistries = new HashSet<RentingRegistry>(0);

    public EmployeeData() {
    }

    public EmployeeData(String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.pwChangeKey = pwChangeKey;
        this.pwChangeDate = pwChangeDate;
    }

    public EmployeeData(int id, String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.pwChangeKey = pwChangeKey;
        this.pwChangeDate = pwChangeDate;
    }

    public EmployeeData(int id, String firstName, String lastName, String phoneNumber, String email, String password, String pwChangeKey, Date pwChangeDate, Set<RoleData> rolesSet, Set<RentingRegistry> rentingRegistries) {
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

    public Set<RoleData> getRolesSet() {
        return rolesSet;
    }

    public void setRolesSet(Set<RoleData> rolesSet) {
        this.rolesSet = rolesSet;
    }

    public Set<RentingRegistry> getRentingRegistries() {
        return rentingRegistries;
    }

    public void setRentingRegistries(Set<RentingRegistry> rentingRegistries) {
        this.rentingRegistries = rentingRegistries;
    }
}
