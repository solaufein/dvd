package pl.radek.dvd.service.genres;

import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.model.Genre;

import java.util.List;

public interface GenresService {
    public List<GenreData> getGenres();
    public List<Genre> getGenresEntity();
    public GenreData getGenre(int id);
    public void deleteGenre(int id);
    public void addGenre(GenreData genre);
    public void updateGenre(GenreData genre);
}
