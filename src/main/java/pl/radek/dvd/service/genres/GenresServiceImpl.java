package pl.radek.dvd.service.genres;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.logic.genres.GenreDAO;
import pl.radek.dvd.model.Genre;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:28
 */
@Service
public class GenresServiceImpl implements GenresService {

    private static Logger logger = Logger.getLogger(GenresServiceImpl.class);

    @Autowired
    private GenreDAO genreDAO;

    public void setGenreDAO(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public List<GenreData> getGenres() {
        List<Genre> genres = genreDAO.getGenres();
        List<GenreData> genreDatas = convertGenreListToGenreDataList(genres);
        return genreDatas;
    }

    @Override
    public List<Genre> getGenresEntity() {
        return genreDAO.getGenres();
    }

    @Override
    public GenreData getGenre(int id) {
        Genre genre = genreDAO.getGenre(id);
        GenreData genreData = convertGenreToGenreData(genre);

        return genreData;
    }

    @Override
    public void deleteGenre(int id) {
        genreDAO.deleteGenre(id);
    }

    @Override
    public void addGenre(GenreData genre) {
        Genre g = convertGenreDataToGenre(genre);
        genreDAO.addGenre(g);
    }

    @Override
    public void updateGenre(GenreData genre) {
        Genre g = convertGenreDataToGenre(genre);
        genreDAO.updateGenre(g);
    }

    private Genre convertGenreDataToGenre(GenreData genre) {
        Genre g = new Genre();
        g.setId(genre.getId());
        g.setGenre(genre.getName());
        g.setMovies(genre.getMovies());
        return g;
    }

    private GenreData convertGenreToGenreData(Genre genre) {
        GenreData genreData = new GenreData();
        genreData.setId(genre.getId());
        genreData.setName(genre.getGenre());
        genreData.setMovies(genre.getMovies());
        return genreData;
    }

    private List<GenreData> convertGenreListToGenreDataList(List<Genre> genres) {
        List<GenreData> genreDatas = new LinkedList<GenreData>();
        for (Genre genre : genres) {
            GenreData genreData = convertGenreToGenreData(genre);
            genreDatas.add(genreData);
        }
        return genreDatas;
    }
}
