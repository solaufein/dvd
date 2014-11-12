package pl.radek.dvd.editor.movies;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import pl.radek.dvd.service.movies.MoviesFacade;

import java.util.Set;

/**
 * Created by Sola on 2014-11-12.
 */
public class ActorCollectionsEditor extends CustomCollectionEditor {
    private static Logger logger = Logger.getLogger(ActorCollectionsEditor.class);

    private MoviesFacade moviesFacade;

    public ActorCollectionsEditor(Class collectionType, boolean nullAsEmptyCollection, MoviesFacade moviesFacade) {
        super(collectionType, nullAsEmptyCollection);
        this.moviesFacade = moviesFacade;
    }

    @Override
    protected Object convertElement(Object element) {
        logger.info("Inside method convert Element in ActorCollectionsEditor");
        try {
            logger.info("element toString = " + element.toString());
            // forms should return the id as the itemValue
            return moviesFacade.getActor(Integer.valueOf(element.toString()));

        } catch (NumberFormatException e) {
            logger.warn("Unable to convert " + element + " to an integer");
            return null;
        }
    }
}
