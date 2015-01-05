package pl.radek.dvd.dto.movies;

import javax.validation.constraints.Pattern;

public class FiltreMovieForm {

    @Pattern(regexp = "^\\s*$|^[A-Za-ząęśćńółżź]*$", message = "{Pattern.filtreclientform.firstName}")
    private String title;

    private String genre;
    private String promotion;
    private String actor;

    public FiltreMovieForm() {
    }

    public FiltreMovieForm(String title, String genre, String promotion, String actor) {
        this.title = title;
        this.genre = genre;
        this.promotion = promotion;
        this.actor = actor;
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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
