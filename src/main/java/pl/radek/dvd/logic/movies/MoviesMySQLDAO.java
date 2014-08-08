package pl.radek.dvd.logic.movies;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.dto.movies.MoviesData;
import pl.radek.dvd.logic.builder.MovieFiltreChoice;
import pl.radek.dvd.logic.builder.MultiFiltreChoice;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Genre;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.logic.builder.ChoiceFiltreQueryFactory;
import pl.radek.dvd.model.Promotion;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-14
 * Time: 14:50
 */

@Repository
@Transactional
public class MoviesMySQLDAO implements MoviesDAO {

    private static Logger logger = Logger.getLogger(MoviesMySQLDAO.class);

    private JdbcTemplate jdbcTemplate;
    private HibernateTemplate hibernateTemplate;
    private MultiFiltreChoice multiFiltreChoice;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Movie> getMovies() {
        logger.debug("Perform method getMovies");

        String hql = "from Movie";

        List<Movie> movies = hibernateTemplate.find(hql);

        return movies;
    }

    @Override
    public List<MoviesData> getMovies(ListDataRequest listDataRequest) {
        logger.debug("Perform method getMovies - listDataRequest");
        multiFiltreChoice = ChoiceFiltreQueryFactory.getMultiFiltreChoice(listDataRequest, hibernateTemplate);
        Query q = multiFiltreChoice.filtreQuery();

        logger.debug("Got sorted and filtered Movies list from db");
        return q.list();
    }

    @Override
    public Movie getMovie(int id) {
        logger.debug("Getting Movie by id: " + id);
        Session session = hibernateTemplate.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        Movie movie = (Movie) session.get(Movie.class, id);

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        Hibernate.initialize(movie.getGenre());
        Hibernate.initialize(movie.getPromotion());
        //todo:  initialize actor Set also ?

        transaction.commit();
        session.flush();
        session.close();

       /* Movie movie = (Movie) hibernateTemplate.get(Movie.class, id);

        Genre genre = movie.getGenre();
        Promotion promotion = movie.getPromotion();

        hibernateTemplate.initialize(genre.getGenre());
        hibernateTemplate.initialize(promotion.getName());
        // initialize actor Set also ?*/

        logger.debug("Got Movie by id: " + id);
        return movie;
    }

    @Override
    public void deleteMovie(int id) {
        logger.debug("Deleting Movie by id: " + id);

        hibernateTemplate.delete(hibernateTemplate.get(Movie.class, id));
        //      hibernateTemplate.bulkUpdate("delete from Movie where id = " + id);

        logger.debug("Deleted Movie");
    }

    @Override
    public void addMovie(Movie movie) {
        String title = movie.getTitle();
        String director = movie.getDirector();
        String productionYear = movie.getProductionYear();
        String description = movie.getDescription();

        logger.debug("Adding movie to db: title = " + title + ", director = " + director + ", productionYear = " + productionYear + ", description = " + description);

        hibernateTemplate.save(movie);

        logger.debug("Added movie to db: title = " + title + ", director = " + director + ", productionYear = " + productionYear + ", description = " + description);
    }

    @Override
    public void updateMovie(Movie movie) {
        int id = movie.getId();
        String title = movie.getTitle();
        String director = movie.getDirector();
        String productionYear = movie.getProductionYear();
        String description = movie.getDescription();

        logger.debug("Updating movie with id = " + id + ": title = " + title + ", director = " + director + ", productionYear = " + productionYear + ", description = " + description);

        hibernateTemplate.update(movie);

        logger.debug("Updated movie with id = " + id + ": title = " + title + ", director = " + director + ", productionYear = " + productionYear + ", description = " + description);

    }

    @Override
    public int getNoOfRecords() {
        logger.debug("Getting total number of records");
        int records;
        String hql = "SELECT COUNT(*) FROM Movie";

        records = DataAccessUtils.intResult(hibernateTemplate.find(hql));
        //      records = jdbcTemplate.queryForInt(hql);
        logger.debug("Got total number of records = " + records);

        return records;
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest) {
        logger.debug("Getting total number of FILTERED movie records");
        //SELECT COUNT(*) FROM Movie WHERE first_name LIKE 'J%' AND pesel LIKE '83%';
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        int records;

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            logger.debug("FilterInfoList is not null and not empty!");

            records = multiFiltreChoice.getNoOfRecords();
        } else {
            logger.debug("FilterInfoList isnull or empty! Get total number of records.");
            records = getNoOfRecords();
        }

        //    int records = DataAccessUtils.intResult(hibernateTemplate.find(query.toString()));
        logger.debug("Got total number of FILTERED records: " + records);
        return records;
    }
}
