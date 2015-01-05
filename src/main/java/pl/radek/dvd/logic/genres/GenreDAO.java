package pl.radek.dvd.logic.genres;

import pl.radek.dvd.model.Genre;

import java.util.List;

public interface GenreDAO {
    public List<Genre> getGenres();
    public Genre getGenre(int id);
    public void deleteGenre(int id);
    public void addGenre(Genre genre);
    public void updateGenre(Genre genre);
}
