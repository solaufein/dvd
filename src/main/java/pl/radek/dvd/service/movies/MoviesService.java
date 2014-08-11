package pl.radek.dvd.service.movies;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 15:26
 */
public interface MoviesService {
    public List<MovieDataDTO> getMovies();
    public MovieDataDTO getMovie(int id);
    public PaginatedList<MoviesData> getMovies(final ListDataRequest request);
    public void deleteMovie(int id);
    public void addMovie(MovieDataDTO movieDataDTO);
    public void addMovie(Movie movie);
    public void updateMovie(MovieDataDTO movieDataDTO);
}
