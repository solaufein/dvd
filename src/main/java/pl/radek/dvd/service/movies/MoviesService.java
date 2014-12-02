package pl.radek.dvd.service.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieBySerialData;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.MoviesRentData;
import pl.radek.dvd.dto.rr.RentData;
import pl.radek.dvd.exceptions.movie.MovieNotFoundException;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 15:26
 */
public interface MoviesService {
    public List<Movie> getMovieByTitle(String title) throws MovieNotFoundException;
    public List<Movie> getMoviesBySerialNumber(String serialNumber) throws MovieNotFoundException;
    public MovieBySerialData getMovieBySerialNumber(String serialNumber) throws MovieNotFoundException;
    public List<MovieDataDTO> getMovies();
    public MovieDataDTO getMovie(int id);
    RentData getMovieRentData(int movieCopyId, short avail);
    public PaginatedList<MoviesData> getMovies(final ListDataRequest request);
    public PaginatedList<MoviesRentData> getMoviesRentData(ListDataRequest request);
    public void deleteMovie(int id);
    public void addMovie(MovieDataDTO movieDataDTO);
    public void addMovie(Movie movie);
    public void updateMovie(MovieDataDTO movieDataDTO);
}
