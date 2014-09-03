package pl.radek.dvd.logic.movieCopy;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.RootEntityResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
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
    public List<MovieCopy> getMovieCopies(ListDataRequest listDataRequest, int movieId) {
        logger.debug("Getting MovieCopies by id: " + movieId);
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.model.MovieCopy(mc.id, mc.serialNumber, mc.conditionInfo, mc.availability) FROM MovieCopy as mc ");
        //   StringBuilder query = new StringBuilder("FROM MovieCopy as mc ");
        query.append("INNER JOIN mc.movie as m ");
        query.append("WHERE m.id = :ide ");
        query.append("ORDER BY mc.availability DESC ");


        Query q = session.createQuery(query.toString());
        //    Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());

        q.setParameter("ide", movieId);

        if (paginationInfo != null) {
            q.setFirstResult(offset);
            q.setMaxResults(recordsPerPage);
        }

        List<MovieCopy> movieCopies = (List<MovieCopy>) q.list();

        //todo: ok ?
        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
     /*   for (MovieCopy movieCopy : movieCopies) {
            Hibernate.initialize(movieCopy.getMovie());
            Hibernate.initialize(movieCopy.getRentingRegistries());
        }*/

        transaction.commit();
        session.flush();
        session.close();

        logger.debug("Got MovieCopies by id: " + movieId);
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

    @SuppressWarnings("unchecked")
    public int getNoOfRecords(int movieId) {
        logger.debug("Getting total number of records for MovieCopyDetails");
        int records;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM MovieCopy as mc ");
        query.append("INNER JOIN mc.movie as m ");
        query.append("WHERE m.id = ").append(movieId);

        List list = hibernateTemplate.find(query.toString());

        records = DataAccessUtils.intResult(list);
        //      records = jdbcTemplate.queryForInt(hql);
        logger.debug("Got total number of records for MovieCopyDetails = " + records);

        return records;
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest, int movieId) {

        return getNoOfRecords(movieId);
    }
}