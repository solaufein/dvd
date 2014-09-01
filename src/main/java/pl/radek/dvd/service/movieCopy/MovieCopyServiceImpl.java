package pl.radek.dvd.service.movieCopy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.logic.movieCopy.MovieCopyDAO;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-01
 * Time: 14:18
 */

@Service
public class MovieCopyServiceImpl implements MovieCopyService {

    private static Logger logger = Logger.getLogger(MovieCopyServiceImpl.class);

    @Autowired
    private MovieCopyDAO movieCopyDAO;

    public void setMovieCopyDAO(MovieCopyDAO movieCopyDAO) {
        this.movieCopyDAO = movieCopyDAO;
    }

    @Override
    public List<MovieCopyDTO> getMovieCopies() {
        return null;
    }

    @Override
    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(ListDataRequest request, int id) {
        return null;
    }

    @Override
    public MovieCopyDTO getMovieCopy(int id) {
        return null;
    }

    @Override
    public void deleteMovieCopy(int id) {

    }

    @Override
    public void addMovieCopy(MovieCopyDTO movieCopy) {

    }

    @Override
    public void updateMovieCopy(MovieCopyDTO movieCopy) {

    }
}
