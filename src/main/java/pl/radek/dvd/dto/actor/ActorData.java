package pl.radek.dvd.dto.actor;

import pl.radek.dvd.model.Movie;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sola on 2014-11-11.
 */
public class ActorData {
    private int id;
    private String firstName;
    private String lastName;
    private Set<Movie> movies = new HashSet<Movie>();

    public ActorData() {
    }

    public ActorData(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ActorData(int id, String firstName, String lastName, Set<Movie> movies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}
