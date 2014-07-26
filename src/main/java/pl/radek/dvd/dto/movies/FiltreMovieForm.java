package pl.radek.dvd.dto.movies;

import javax.validation.constraints.Pattern;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 15:29
 */
public class FiltreMovieForm {

    @Pattern(regexp = "^\\s*$|^[A-Za-ząęśćńółżź]*$", message = "{Pattern.filtreclientform.firstName}")
    private String title;

    private String genre;
    private String promotion;

    // walidacja imienia i nazwiska nie ma sensu, na calym swiecie moga byc rozne znaki i lepiej to dopuscic tak jak
    // uzytkownik to wpisze
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
