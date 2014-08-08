package pl.radek.dvd.utils.movies;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * User: Sola
 * Date: 2014-07-29
 * Time: 16:45
 */
public class MoviePaginationTag extends SimpleTagSupport {
    String order;
    String field;

    String currentPage;
    String noOfPages;

    String title;
    String genre;
    String promotion;
    String actorName;

    public MoviePaginationTag() {
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

        StringBuilder builder = new StringBuilder("?");
        String link = "?";

        if (order != null && field != null && !order.isEmpty() && !field.isEmpty()) {
            if (title != null && !title.equals("")){
                builder.append("title=").append(title).append("&");
            }

            if(genre != null && !genre.equals("")){
                builder.append("genre=").append(genre).append("&");
            }

            if (promotion != null && !promotion.equals("")){
                builder.append("promotion=").append(promotion).append("&");
            }

            if (actorName != null && !actorName.equals("")){
                builder.append("actor=").append(actorName).append("&");
            }
            builder.append("order=").append(order).append("&").append("field=").append(field).append("&").append("currentPage=").append(currentPage);

        } else {
            if (title != null && !title.equals("")){
                builder.append("title=").append(title).append("&");
            }

            if(genre != null && !genre.equals("")){
                builder.append("genre=").append(genre).append("&");
            }

            if (promotion != null && !promotion.equals("")){
                builder.append("promotion=").append(promotion).append("&");
            }

            if (actorName != null && !actorName.equals("")){
                builder.append("actor=").append(actorName).append("&");
            }
            builder.append("currentPage=").append(currentPage);
        }

        link = builder.toString();
        getJspContext().setAttribute("paginlink", link);
    }
}
