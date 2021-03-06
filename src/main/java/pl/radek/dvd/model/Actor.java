package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "actorset", fetch = FetchType.LAZY)
    private Set<Movie> movies = new HashSet<Movie>();

    public Actor() {
    }

    public Actor(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor(int id, String firstName, String lastName, Set<Movie> movies) {
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

    @Override
    /**
     * return JSON formatted String like: {"id" : "5", "name" : "john white"}
     */
    public String toString() {
        String fullName = "\" " + firstName + " " + lastName + "\"}";
        return "{\"id\" : " + "\"" + id + "\"" + ", \"name\" :" + fullName;
    }
}
