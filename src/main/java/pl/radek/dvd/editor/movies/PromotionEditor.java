package pl.radek.dvd.editor.movies;

import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.service.movies.MoviesFacade;

import java.beans.PropertyEditorSupport;

/**
 * User: Sola
 * Date: 2014-08-11
 * Time: 14:05
 */
public class PromotionEditor extends PropertyEditorSupport {

    private MoviesFacade moviesFacade;

    public PromotionEditor(MoviesFacade moviesFacade) {

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
        PromotionData promotionData = moviesFacade.getPromotion(Integer.parseInt(text));
        setValue(promotionData);
    }
}
