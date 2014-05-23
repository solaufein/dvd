package pl.radek.dvd.utils;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * User: Sola
 * Date: 2014-05-23
 * Time: 10:09
 */
public class ClientSortHeaderTag extends SimpleTagSupport {

    String firstName;
    String lastName;
    String pesel;

    String order;
    String field;
    String columnName;

    String currentPage;

    public ClientSortHeaderTag() {
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

    public void setOrder(String order) {
        this.order = order;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        String link = "";

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            link = "?order=" + Constants.DESC + "&field=" + columnName;
            if (!firstName.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty()) {
                link = link + "&firstName=" + firstName + "&lastName=" + lastName + "&pesel=" + pesel;
            }
        } else {
            link = "?order=" + Constants.ASC + "&field=" + columnName;
            if (!firstName.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty()) {
                link = link + "&firstName=" + firstName + "&lastName=" + lastName + "&pesel=" + pesel;
            }
        }

        getJspContext().setAttribute("hlink", link);
    }
}
