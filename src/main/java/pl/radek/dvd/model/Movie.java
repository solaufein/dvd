package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 16:05
 */

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "director", nullable = false)
    private String director;

    @Column(name = "production_year", nullable = false)
    private String productionYear;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movie")
    private Set<MovieCopy> movieCopies = new HashSet<MovieCopy>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @ManyToMany(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
    @JoinTable(name = "starring",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actors_id")})
    private Set<Actor> actorset = new HashSet<Actor>();

    public Movie() {
    }

    public Movie(int id, String title, String director, String productionYear, String description) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.description = description;
    }

    public Movie(int id, String title, String director, String productionYear, String description, Set<MovieCopy> movieCopies, Genre genre, Promotion promotion, Set<Actor> actorset) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Set<Actor> getActorset() {
        return actorset;
    }

    public void setActorset(Set<Actor> actorset) {
        this.actorset = actorset;
    }
}
