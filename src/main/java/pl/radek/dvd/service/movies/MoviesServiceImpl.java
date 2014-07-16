package pl.radek.dvd.service.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.dto.movies.PaginatedListMovies;
import pl.radek.dvd.logic.movies.MoviesDAO;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-16
 * Time: 15:46
 */

@Service
public class MoviesServiceImpl implements MoviesService {

    private static Logger logger = Logger.getLogger(MoviesServiceImpl.class);

    @Autowired
    private MoviesDAO moviesDAO;

    public void setMoviesDAO(MoviesDAO moviesDAO) {
        this.moviesDAO = moviesDAO;
    }

    //todo: convertion Movie to MovieDataPresentation

    @Override
    public List<Movie> getMovies() {
        return moviesDAO.getMovies();
    }

    @Override
    public Movie getMovie(int id) {
        return moviesDAO.getMovie(id);
    }

    @Override
    public PaginatedList<MoviesData> getMovies(ListDataRequest request) {
        List<MoviesData> movies = moviesDAO.getMovies(request);
        int noOfRecords = moviesDAO.getNoOfRecords(request);

        PaginatedListMovies paginatedList = new PaginatedListMovies();
        paginatedList.setMoviesDatas(movies);
        paginatedList.setNoOfRecords(noOfRecords);

        return paginatedList;
    }

    @Override
    public void deleteMovie(int id) {
         moviesDAO.deleteMovie(id);
    }

    @Override
    public void addMovie(Movie client) {
        moviesDAO.addMovie(client);

    }

    @Override
    public void updateMovie(Movie client) {
        moviesDAO.updateMovie(client);
    }
}
