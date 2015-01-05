package pl.radek.dvd.dto.raports;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FilterMoviesNotReturnedForm {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;

    public FilterMoviesNotReturnedForm() {
    }

    public FilterMoviesNotReturnedForm(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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
