package pl.radek.dvd.logic.genres;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.model.Genre;

import java.util.List;

@Repository
public class GenreMySQLDAO implements GenreDAO {

    private static Logger logger = Logger.getLogger(GenreMySQLDAO.class);

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
    public List<Genre> getGenres() {
        logger.debug("Perform method getGenres");

        String hql = "from Genre";

        List<Genre> genre = hibernateTemplate.find(hql);

        return genre;
    }

    @Override
    public Genre getGenre(int id) {
        logger.debug("Getting Genre by id: " + id);

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Genre genre = (Genre) session.get(Genre.class, id);

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        Hibernate.initialize(genre.getMovies());

        logger.debug("Got Genre by id: " + id);
        return genre;
    }

    @Override
    public void deleteGenre(int id) {
        logger.debug("Deleting Genre by id: " + id);

        hibernateTemplate.delete(hibernateTemplate.get(Genre.class, id));
        //      hibernateTemplate.bulkUpdate("delete from Genre where id = " + id);

        logger.debug("Deleted Genre with id = " + id);
    }

    @Override
    public void addGenre(Genre genre) {
        hibernateTemplate.save(genre);

    }

    @Override
    public void updateGenre(Genre genre) {
        hibernateTemplate.update(genre);
    }
}
