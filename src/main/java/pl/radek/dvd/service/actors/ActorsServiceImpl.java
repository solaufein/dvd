package pl.radek.dvd.service.actors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.actor.ActorDataTag;
import pl.radek.dvd.logic.actors.ActorsDAO;
import pl.radek.dvd.model.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sola on 2014-11-11.
 */

@Service
@Transactional
public class ActorsServiceImpl implements ActorsService {
    private static Logger logger = Logger.getLogger(ActorsServiceImpl.class);

    @Autowired
    private ActorsDAO actorsDAO;

    public void setActorsDAO(ActorsDAO actorsDAO) {
        this.actorsDAO = actorsDAO;
    }

    @Override
    public Actor getActor(int id) {
        return actorsDAO.getActor(id);
    }

    @Override
    public List<ActorDataTag> getActorTags(String tagName) {

        List<Actor> actorsByName = actorsDAO.getActorsByName(tagName);
        List<ActorDataTag> actorDataTags = convertActorListToActorDataTagList(actorsByName);

        return actorDataTags;
    }

    private List<ActorDataTag> convertActorListToActorDataTagList(List<Actor> actorsByName) {
        List<ActorDataTag> actorDataTags = new ArrayList<ActorDataTag>();

        for (Actor actor : actorsByName) {
            ActorDataTag actorDataTag = convertActorToActorDataTag(actor);
            actorDataTags.add(actorDataTag);
        }

        return actorDataTags;
    }

    private ActorDataTag convertActorToActorDataTag(Actor actor) {
        StringBuilder tag = new StringBuilder();
        tag.append(actor.getFirstName()).append(" ").append(actor.getLastName());
        logger.info("Actor TAG String builder = " + tag);

        ActorDataTag actorDataTag = new ActorDataTag();
        actorDataTag.setId(actor.getId());
        actorDataTag.setTag(tag.toString());

        return actorDataTag;
    }
}
