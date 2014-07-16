package pl.radek.dvd.service.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 16:01
 */
public class MoviesFacadeImpl implements MoviesFacade {
    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public Movie getMovie(int id) {
        return null;
    }

    @Override
    public PaginatedList<MoviesData> getMovies(ListDataRequest request) {
        return null;
    }

    @Override
    public void deleteMovie(int id) {

    }

    @Override
    public void addMovie(Movie client) {

    }

    @Override
    public void updateMovie(Movie client) {

    }
}
