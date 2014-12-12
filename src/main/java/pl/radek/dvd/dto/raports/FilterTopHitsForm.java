package pl.radek.dvd.dto.raports;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Sola on 2014-12-11.
 */
public class FilterTopHitsForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    private String genre;
    private String promotion;

    public FilterTopHitsForm() {
    }

    public FilterTopHitsForm(Date dateFrom, Date dateTo, String genre, String promotion) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.genre = genre;
        this.promotion = promotion;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
