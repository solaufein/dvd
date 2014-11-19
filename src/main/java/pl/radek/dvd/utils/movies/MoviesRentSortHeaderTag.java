package pl.radek.dvd.utils.movies;

import pl.radek.dvd.model.Constants;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by Sola on 2014-11-19.
 */
public class MoviesRentSortHeaderTag extends SimpleTagSupport {
    String clientId;
    String title;
    String genre;
    String promotion;
    String actorName;

    String order;
    String field;
    String columnName;

    public MoviesRentSortHeaderTag() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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
        StringBuilder builder = new StringBuilder("?");
        builder.append("id=").append(clientId).append("&");

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            if (title != null && !title.equals("")) {
                builder.append("title=").append(title).append("&");
            }

            if (genre != null && !genre.equals("")) {
                builder.append("genre=").append(genre).append("&");
            }

            if (promotion != null && !promotion.equals("")) {
                builder.append("promotion=").append(promotion).append("&");
            }

            if (actorName != null && !actorName.equals("")) {
                builder.append("actor=").append(actorName).append("&");
            }
            builder.append("order=").append(Constants.DESC).append("&").append("field=").append(columnName);

        } else {
            if (title != null && !title.equals("")) {
                builder.append("title=").append(title).append("&");
            }

            if (genre != null && !genre.equals("")) {
                builder.append("genre=").append(genre).append("&");
            }

            if (promotion != null && !promotion.equals("")) {
                builder.append("promotion=").append(promotion).append("&");
            }

            if (actorName != null && !actorName.equals("")) {
                builder.append("actor=").append(actorName).append("&");
            }
            builder.append("order=").append(Constants.ASC).append("&").append("field=").append(columnName);

        }
        link = builder.toString();

        getJspContext().setAttribute("hlink", link);
    }
}
