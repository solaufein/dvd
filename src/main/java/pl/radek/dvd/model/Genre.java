package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 16:55
 */

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "genre", nullable = false)
    private String genre;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genre")
    private Set<Movie> movies = new HashSet<Movie>(0);

    public Genre() {
    }

    public Genre(int id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Genre(int id, String genre, Set<Movie> movies) {
        this.id = id;
        this.genre = genre;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return genre;
    }
}
