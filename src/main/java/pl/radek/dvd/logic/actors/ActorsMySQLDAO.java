package pl.radek.dvd.logic.actors;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.model.Actor;

import java.util.List;

@Repository
public class ActorsMySQLDAO implements ActorsDAO {
    private static Logger logger = Logger.getLogger(ActorsMySQLDAO.class);

    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Actor getActor(int id) {
        logger.info("Get Actor By id = " + id);
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        Actor actor = (Actor) session.get(Actor.class, id);

        //   Hibernate.initialize(actor.getMovies());

        logger.info("Got Actor");
        return actor;
    }

    @Override
    public List<Actor> getActorsByName(String actorName) {
        logger.info("Get Actors By Name entered. name = " + actorName);

        StringBuilder hql = new StringBuilder();
        hql.append("FROM Actor a ");
        hql.append("WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE :afull");

        String query = hql.toString();

        logger.info("query = " + query);

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("afull", "%" + actorName + "%");

        List<Actor> actorList = (List<Actor>) q.list();

        logger.info("Get Actors By Name exit.");
        return actorList;
    }
}
