package pl.radek.dvd.logic.genres;

import pl.radek.dvd.model.Genre;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:14
 */
public interface GenreDAO {
    public List<Genre> getGenres();
    public Genre getGenre(int id);
    public void deleteGenre(int id);
    public void addGenre(Genre genre);
    public void updateGenre(Genre genre);
}
