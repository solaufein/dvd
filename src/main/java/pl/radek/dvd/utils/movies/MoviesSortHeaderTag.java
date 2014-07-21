package pl.radek.dvd.utils.movies;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * User: Sola
 * Date: 2014-07-21
 * Time: 12:32
 */
public class MoviesSortHeaderTag extends SimpleTagSupport {
    String title;
    String genre;
    String promotion;
    String actorName;

    String order;
    String field;
    String columnName;

    public MoviesSortHeaderTag() {
    }

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

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        String link = "";

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            link = "?order=" + Constants.DESC + "&field=" + columnName;
            /*if (!firstName.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty()) {
                link = link + "&firstName=" + firstName + "&lastName=" + lastName + "&pesel=" + pesel;
            }*/
        } else {
            link = "?order=" + Constants.ASC + "&field=" + columnName;
            /*if (!firstName.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty()) {
                link = link + "&firstName=" + firstName + "&lastName=" + lastName + "&pesel=" + pesel;
            }*/
        }

        getJspContext().setAttribute("hlink", link);
    }
}
