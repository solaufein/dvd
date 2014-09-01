package pl.radek.dvd.logic.movieCopy;

import pl.radek.dvd.model.MovieCopy;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-01
 * Time: 14:03
 */
public interface MovieCopyDAO {
    public List<MovieCopy> getMovieCopies();
    public MovieCopy getMovieCopy(int id);
    public void deleteMovieCopy(int id);
    public void addMovieCopy(MovieCopy movieCopy);
    public void updateMovieCopy(MovieCopy movieCopy);
}
