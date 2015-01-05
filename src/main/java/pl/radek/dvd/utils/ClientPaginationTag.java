package pl.radek.dvd.utils;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ClientPaginationTag extends SimpleTagSupport {
    String order;
    String field;

    String currentPage;
    String noOfPages;

    String firstName;
    String lastName;
    String pesel;

    public ClientPaginationTag() {
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

    public void setNoOfPages(String noOfPages) {
        this.noOfPages = noOfPages;
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

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        StringBuilder builder = new StringBuilder("?");
        String link = "?";

        if (order != null && field != null && !order.isEmpty() && !field.isEmpty()) {
            if (firstName != null && !firstName.equals("")){
              builder.append("firstName=").append(firstName).append("&");
            }

            if(lastName != null && !lastName.equals("")){
                builder.append("lastName=").append(lastName).append("&");
            }

            if (pesel != null && !pesel.equals("")){
                builder.append("pesel=").append(pesel).append("&");
            }
            builder.append("order=").append(order).append("&").append("field=").append(field).append("&").append("currentPage=").append(currentPage);

        } else {
            if (firstName != null && !firstName.equals("")){
                builder.append("firstName=").append(firstName).append("&");
            }

            if(lastName != null && !lastName.equals("")){
                builder.append("lastName=").append(lastName).append("&");
            }

            if (pesel != null && !pesel.equals("")){
                builder.append("pesel=").append(pesel).append("&");
            }
            builder.append("currentPage=").append(currentPage);
        }

        link = builder.toString();
        getJspContext().setAttribute("paginlink", link);
    }
}
