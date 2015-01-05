package pl.radek.dvd.exceptions.movie;

public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
