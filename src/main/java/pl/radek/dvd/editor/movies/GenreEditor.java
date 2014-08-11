package pl.radek.dvd.editor.movies;

import pl.radek.dvd.dto.genres.GenreData;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.service.movies.MoviesFacade;

import java.beans.PropertyEditorSupport;

/**
 * User: Sola
 * Date: 2014-08-11
 * Time: 13:55
 */
public class GenreEditor extends PropertyEditorSupport {
    private MoviesFacade moviesFacade;

    public GenreEditor(MoviesFacade moviesFacade) {

        this.moviesFacade = moviesFacade;
    }

    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        GenreData genre = moviesFacade.getGenre(Integer.parseInt(text));
        setValue(genre);
    }
}
