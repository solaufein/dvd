package pl.radek.dvd.service.genres;

import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.model.Genre;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:27
 */
public interface GenresService {
    public List<GenreData> getGenres();
    public GenreData getGenre(int id);
    public void deleteGenre(int id);
    public void addGenre(GenreData genre);
    public void updateGenre(GenreData genre);
}
