package pl.radek.dvd.dto.clients;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * User: Sola
 * Date: 2014-03-27
 * Time: 14:00
 */
public class ClientData {

    private int id;

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "{Pattern.clientdata.firstName}")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "{Pattern.clientdata.lastName}")
    private String lastName;

    @Pattern(regexp = "\\d{11}", message = "{Pattern.clientdata.pesel}")
    private String pesel;

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "{Pattern.clientdata.city}")
    private String city;

    @NotEmpty(message = "{NotEmpty.clientdata.street}")
    private String street;

    @NotEmpty(message = "{NotEmpty.clientdata.phoneNumber}")
    @Pattern(regexp = "[0-9()-\\.]*", message = "{Pattern.clientdata.phoneNumber}")
    private String phoneNumber;

    @NotEmpty(message = "{NotEmpty.clientdata.email}")
    @Email(message = "{Email.clientdata.email}")
    private String email;


    public ClientData() {
    }

    public ClientData(String firstName, String lastName, String pesel, String city, String street, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.city = city;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public ClientData(int id, String firstName, String lastName, String pesel, String city, String street, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.city = city;
        this.street = street;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }


    // GETTERS
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

