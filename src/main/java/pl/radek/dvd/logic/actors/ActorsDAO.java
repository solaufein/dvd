package pl.radek.dvd.logic.actors;

import pl.radek.dvd.model.Actor;

import java.util.List;

/**
 * Created by Sola on 2014-11-11.
 */
public interface ActorsDAO {
    public Actor getActor(int id);
    public List<Actor> getActorsByName(String actorName);
}
