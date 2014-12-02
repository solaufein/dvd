package pl.radek.dvd.exceptions.movie;

/**
 * Created by Sola on 2014-12-02.
 */
public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
