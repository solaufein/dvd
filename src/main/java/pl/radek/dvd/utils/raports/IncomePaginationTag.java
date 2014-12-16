package pl.radek.dvd.utils.raports;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Sola on 2014-12-15.
 */
public class IncomePaginationTag extends SimpleTagSupport {
    String order;
    String field;

    String currentPage;
    String noOfPages;

    String dateFrom;
    String dateTo;
    String genre;
    String section;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(String noOfPages) {
        this.noOfPages = noOfPages;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public IncomePaginationTag() {
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();

        StringBuilder builder = new StringBuilder("?");
        String link = "?";

        if (dateFrom != null && !dateFrom.equals("")) {
            builder.append("dateFrom=").append(dateFrom).append("&");
        }

        if (dateTo != null && !dateTo.equals("")) {
            builder.append("dateTo=").append(dateTo).append("&");
        }

        if (genre != null && !genre.equals("")) {
            builder.append("genre=").append(genre).append("&");
        }

        if (section != null && !section.equals("")) {
            builder.append("section=").append(section).append("&");
        }
        builder.append("currentPage=").append(currentPage);

        link = builder.toString();
        getJspContext().setAttribute("paginlink", link);
    }
}
