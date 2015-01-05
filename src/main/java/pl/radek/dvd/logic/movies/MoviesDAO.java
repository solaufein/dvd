package pl.radek.dvd.logic.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieBySerialData;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.MoviesRentData;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.dto.rr.ReturnData;
import pl.radek.dvd.exceptions.movie.MovieNotFoundException;
import pl.radek.dvd.model.Movie;

import java.util.List;

public interface MoviesDAO {
    public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException;
    public List<Movie> getMoviesBySerialNumber(String serialNumber) throws MovieNotFoundException;
    public MovieBySerialData getMovieBySerialNumber(String serialNumber) throws MovieNotFoundException;
    public List<Movie> getMovies();
    public List<MoviesData> getMovies(ListDataRequest listDataRequest);
    public PaginatedList<MoviesRentData> getMoviesRentDataPaginated(ListDataRequest listDataRequest);
    public Movie getMovie(int id);
    RentData getMovieRentData(int movieCopyId, short avail);
    ReturnData getMovieReturnData(int movieCopyId, short avail);
    public void deleteMovie(int id);
    public void addMovie(Movie movie);
    public void updateMovie(Movie movie);
    public int getNoOfRecords();
    public int getNoOfRecords(ListDataRequest listDataRequest);
}
