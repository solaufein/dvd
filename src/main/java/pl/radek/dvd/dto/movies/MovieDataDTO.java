package pl.radek.dvd.dto.movies;

import org.hibernate.validator.constraints.NotEmpty;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Actor;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.MovieCopy;
import pl.radek.dvd.model.Promotion;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class MovieDataDTO {
    private int id;

    @NotEmpty(message = "{NotEmpty.moviedatadto.title}")
    private String title;

    @NotEmpty(message = "{NotEmpty.moviedatadto.director}")
    private String director;

    @Pattern(regexp = "\\d{4}", message = "{Pattern.moviedatadto.productionyear}")
    private String productionYear;

    private BigDecimal price;
    private String description;
    private Set<MovieCopy> movieCopies;
    private GenreData genre;
    private PromotionData promotion;
    private Set<Actor> actorset;

    public MovieDataDTO() {
    }

    public MovieDataDTO(int id, String title, String director, String productionYear, BigDecimal price, String description) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.price = price;
        this.description = description;
    }

    public MovieDataDTO(int id, String title, String director, String productionYear, BigDecimal price, String description, Set<MovieCopy> movieCopies, GenreData genre, PromotionData promotion, Set<Actor> actorset) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.price = price;
        this.description = description;
        this.movieCopies = movieCopies;
        this.genre = genre;
        this.promotion = promotion;
        this.actorset = actorset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<MovieCopy> getMovieCopies() {
        return movieCopies;
    }

    public void setMovieCopies(Set<MovieCopy> movieCopies) {
        this.movieCopies = movieCopies;
    }

    public GenreData getGenre() {
        return genre;
    }

    public void setGenre(GenreData genre) {
        this.genre = genre;
    }

    public PromotionData getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionData promotion) {
        this.promotion = promotion;
    }

    public Set<Actor> getActorset() {
        return actorset;
    }

    public void setActorset(Set<Actor> actorset) {
        this.actorset = actorset;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
