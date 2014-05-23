package pl.radek.dvd.dto;

import javax.validation.constraints.Pattern;

/**
 * User: Sola
 * Date: 2014-05-22
 * Time: 12:24
 */
public class FiltreClientForm {

    @Pattern(regexp = "^\\s*$|^[A-Za-ząęśćńółżź]*$", message = "{Pattern.filtreclientform.firstName}")
    private String firstName;

    @Pattern(regexp = "^\\s*$|^[A-Za-ząęśćńółżź]*$", message = "{Pattern.filtreclientform.lastName}")
    private String lastName;

    @Pattern(regexp = "^\\s*$|\\d*$", message = "{Pattern.filtreclientform.pesel}")
    private String pesel;

    public FiltreClientForm() {
    }

    public FiltreClientForm(String firstName, String lastName, String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
