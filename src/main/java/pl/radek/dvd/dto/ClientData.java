package pl.radek.dvd.dto;

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

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "Pattern.client.firstName")
    private String firstName;

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "Pattern.client.lastName")
    private String lastName;

    @Pattern(regexp = "\\d{11}", message = "Pattern.client.pesel")
    private String pesel;

    @Pattern(regexp = "^[A-Z][a-ząęśćńółżź]+$", message = "Pattern.client.city")
    private String city;

    @NotEmpty(message = "NotEmpty.client.street")
    private String street;

    @NotEmpty(message = "NotEmpty.client.phoneNumber")
    @Pattern(regexp = "[0-9()-\\.]*", message = "Pattern.client.phoneNumber")
    private String phoneNumber;

    @NotEmpty(message = "NotEmpty.client.email")
    @Email(message = "Email.client.email")
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

