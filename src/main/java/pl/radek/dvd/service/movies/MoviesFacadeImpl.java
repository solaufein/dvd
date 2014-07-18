package pl.radek.dvd.service.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

@Component
public class MoviesFacadeImpl implements MoviesFacade {

    private static Logger logger = Logger.getLogger(MoviesFacadeImpl.class);

    @Autowired
    private MoviesService moviesService;

    public void setMoviesService(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @Override
    public List<Movie> getMovies() {
        return moviesService.getMovies();
    }

    @Override
    public Movie getMovie(int id) {
        return moviesService.getMovie(id);
    }

    @Override
    public PaginatedList<MoviesData> getMovies(ListDataRequest request) {
        return moviesService.getMovies(request);
    }

    @Override
    public void deleteMovie(int id) {
        moviesService.deleteMovie(id);
    }

    @Override
    public void addMovie(Movie movie) {
        moviesService.addMovie(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        moviesService.updateMovie(movie);
    }
}
