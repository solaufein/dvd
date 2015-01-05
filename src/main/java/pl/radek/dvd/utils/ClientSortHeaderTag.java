package pl.radek.dvd.utils;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

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
        StringBuilder builder = new StringBuilder("?");
        String link = "";

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            if (firstName != null && !firstName.equals("")) {
                builder.append("firstName=").append(firstName).append("&");
            }

            if (lastName != null && !lastName.equals("")) {
                builder.append("lastName=").append(lastName).append("&");
            }

            if (pesel != null && !pesel.equals("")) {
                builder.append("pesel=").append(pesel).append("&");
            }
            builder.append("order=").append(Constants.DESC).append("&").append("field=").append(columnName);

        } else {
            if (firstName != null && !firstName.equals("")) {
                builder.append("firstName=").append(firstName).append("&");
            }

            if (lastName != null && !lastName.equals("")) {
                builder.append("lastName=").append(lastName).append("&");
            }

            if (pesel != null && !pesel.equals("")) {
                builder.append("pesel=").append(pesel).append("&");
            }
            builder.append("order=").append(Constants.ASC).append("&").append("field=").append(columnName);

        }
        link = builder.toString();

        getJspContext().setAttribute("hlink", link);
    }
}
