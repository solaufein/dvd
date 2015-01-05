package pl.radek.dvd.dto.genres;

import pl.radek.dvd.model.Movie;

import java.util.HashSet;
import java.util.Set;

public class GenreData {
    private int id;
    private String name;
    private Set<Movie> movies;

    public GenreData() {
    }

    public GenreData(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public GenreData(int id, String name, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return name;
    }


}
