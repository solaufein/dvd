package pl.radek.dvd.dto.raports;

public class TopHitsDto {
    private String title;
    private Number loanCount;

    public TopHitsDto() {
    }

    public TopHitsDto(String title, Number loanCount) {
        this.title = title;
        this.loanCount = loanCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Number getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(Number loanCount) {
        this.loanCount = loanCount;
    }
}
