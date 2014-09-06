package pl.radek.dvd.service.movieCopy;

import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.movies.MovieCopyDTO;
import pl.radek.dvd.dto.movies.MovieDataDTO;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.MovieCopy;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-01
 * Time: 14:18
 */
public interface MovieCopyService {
    public List<MovieCopyDTO> getMovieCopies();

    public PaginatedList<MovieCopyDTO> getMovieCopyPaginatedList(final ListDataRequest request, int movieId);

    public MovieCopyDTO getMovieCopy(int id);

    public void deleteMovieCopy(int id);

    public void addMovieCopy(int movieId ,MovieCopyDTO movieCopy);

    public void updateMovieCopy(MovieCopyDTO movieCopy);
}
