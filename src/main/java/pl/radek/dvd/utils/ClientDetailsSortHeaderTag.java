package pl.radek.dvd.utils;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ClientDetailsSortHeaderTag extends SimpleTagSupport {
    String order;
    String field;
    String columnName;

    String currentPage;

    public ClientDetailsSortHeaderTag() {
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
        } else {
            link = "?order=" + Constants.ASC + "&field=" + columnName;
        }

        getJspContext().setAttribute("hlink", link);
    }
}
