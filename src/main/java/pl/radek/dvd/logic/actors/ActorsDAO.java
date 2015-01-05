package pl.radek.dvd.logic.actors;

import pl.radek.dvd.model.Actor;

import java.util.List;

public interface ActorsDAO {
    public Actor getActor(int id);
    public List<Actor> getActorsByName(String actorName);
}
