package pl.radek.dvd.logic.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-14
 * Time: 14:47
 */
public interface MoviesDAO {

    public List<Movie> getMovies();
    public List<MoviesData> getMovies(ListDataRequest listDataRequest);
    public Movie getMovie(int id);
    public void deleteMovie(int id);
    public void addMovie(Movie movie);
    public void updateMovie(Movie movie);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
