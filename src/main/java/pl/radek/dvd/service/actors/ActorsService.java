package pl.radek.dvd.service.actors;

import pl.radek.dvd.dto.actor.ActorDataTag;

import java.util.List;

/**
 * Created by Sola on 2014-11-11.
 */
public interface ActorsService {
    public List<ActorDataTag> getActorTags(String tagName);
}
