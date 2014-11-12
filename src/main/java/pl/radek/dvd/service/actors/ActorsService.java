package pl.radek.dvd.service.actors;

import pl.radek.dvd.dto.actor.ActorDataTag;
import pl.radek.dvd.model.Actor;

import java.util.List;

/**
 * Created by Sola on 2014-11-11.
 */
public interface ActorsService {
    public Actor getActor(int id);
    public List<ActorDataTag> getActorTags(String tagName);
}
