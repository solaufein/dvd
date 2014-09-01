package pl.radek.dvd.logic.movieCopy;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.model.MovieCopy;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-01
 * Time: 14:03
 */

@Repository
@Transactional
public class MovieCopyMySQLDAO implements MovieCopyDAO {
    private static Logger logger = Logger.getLogger(MovieCopyMySQLDAO.class);

    private JdbcTemplate jdbcTemplate;
    private HibernateTemplate hibernateTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    @Override
    public List<MovieCopy> getMovieCopies() {
        logger.debug("Perform method getMovieCopies");

        String hql = "from MovieCopy";

        List<MovieCopy> movieCopies = hibernateTemplate.find(hql);

        return movieCopies;
    }

    @Override
    public MovieCopy getMovieCopy(int id) {
        logger.debug("Getting MovieCopy by id: " + id);

        //  MovieCopy movieCopy= (MovieCopy) hibernateTemplate.get(MovieCopy.class, id);

        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        MovieCopy movieCopy = (MovieCopy) session.get(MovieCopy.class, id);

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        Hibernate.initialize(movieCopy.getMovie());
        Hibernate.initialize(movieCopy.getRentingRegistries());

        transaction.commit();
        session.flush();
        session.close();

        logger.debug("Got MovieCopy by id: " + id);
        return movieCopy;
    }

    @Override
    public void deleteMovieCopy(int id) {
        logger.debug("Deleting MovieCopy by id: " + id);

        MovieCopy movieCopy = hibernateTemplate.get(MovieCopy.class, id);
        hibernateTemplate.delete(movieCopy);

        logger.debug("Deleted MovieCopy with id: " + id);
    }

    @Override
    public void addMovieCopy(MovieCopy movieCopy) {
        hibernateTemplate.save(movieCopy);
    }

    @Override
    public void updateMovieCopy(MovieCopy movieCopy) {
        hibernateTemplate.update(movieCopy);
    }
}
