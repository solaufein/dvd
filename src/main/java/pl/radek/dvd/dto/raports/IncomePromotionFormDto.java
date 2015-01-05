package pl.radek.dvd.dto.raports;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class IncomePromotionFormDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTo;
    private String section;
    private String genre;

    public IncomePromotionFormDto() {
    }

    public IncomePromotionFormDto(Date dateFrom, Date dateTo, String section, String genre) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.section = section;
        this.genre = genre;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
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
