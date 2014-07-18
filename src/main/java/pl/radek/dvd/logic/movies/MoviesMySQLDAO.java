package pl.radek.dvd.logic.movies;

import org.apache.log4j.Logger;
import org.hibernate.Query;
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
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Movie;

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
        //todo:  actor filtering
     /*
        SELECT m.id , m.title, m.director, m.production_year, g.genre, p.name
        FROM movie as m
        LEFT JOIN genre as g ON g.id = m.genre_id
        LEFT JOIN promotion as p ON p.id = m.promotion_id
        LEFT JOIN starring as s ON m.id = s.movie_id
        LEFT JOIN actors as a ON s.actors_id = a.id
        WHERE m.title LIKE '%S%' AND a.id = 1 AND g.id= 5 AND p.id = 1
        ORDER BY m.title ASC LIMIT 0,9;
    */

        logger.debug("Perform method getMovies - listDataRequest");

        final String hql = "FROM Movie";
        List<Movie> movies = null;
        SortInfo sortInfo = listDataRequest.getSortInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.movies.MoviesData(m.id , m.title, m.director, m.productionYear, g.genre, p.name) FROM Movie as m ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");

        boolean isFirst = true;

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            query.append("LEFT JOIN m.actorSet as a ");
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    if (isFirst) {
                        query.append(" WHERE m.title LIKE :mtitle");
                    } else {
                        query.append(" AND m.title LIKE :mtitle");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    if (isFirst) {
                        query.append(" WHERE g.genre = :genre");
                    } else {
                        query.append(" AND g.genre = :genre");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    if (isFirst) {
                        query.append(" WHERE p.name = :pname");
                    } else {
                        query.append(" AND p.name = :pname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    //todo: actor filtering
                }
            }
        }

        if (sortInfo != null) {
            String field = sortInfo.getOrderField();
            boolean isAsc = sortInfo.isAsc();
            String order;

            if (isAsc == true) {
                order = Constants.ASC;
            } else {
                order = Constants.DESC;
            }

            query.append(" ORDER BY " + field + " " + order);
        }

        Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    q.setParameter("mtitle", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    q.setParameter("genre", filterInfo.getFilterData());
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    q.setParameter("pname", filterInfo.getFilterData());
                }

                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    //todo: actor filtering parameter
                }
            }
        }

        if (paginationInfo != null) {
            q.setFirstResult(offset);
            q.setMaxResults(recordsPerPage);
        }

        logger.debug("Got sorted and filtered Movies list from db");
        return q.list();
    }

    @Override
    public Movie getMovie(int id) {
        logger.debug("Getting Movie by id: " + id);

        Movie movie = (Movie) hibernateTemplate.get(Movie.class, id);

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
        //todo:

        logger.debug("Getting total number of FILTERED movie records");
        //SELECT COUNT(*) FROM Movie WHERE first_name LIKE 'J%' AND pesel LIKE '83%';
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Movie m ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");

        Query q;
        int records;
        boolean isFirst = true;

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            logger.debug("FilterInfoList is not null and not empty!");
            query.append("LEFT JOIN m.actorSet as a ");
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    if (isFirst) {
                        query.append(" WHERE m.title LIKE :mtitle");
                    } else {
                        query.append(" AND m.title LIKE :mtitle");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    if (isFirst) {
                        query.append(" WHERE g.genre = :genre");
                    } else {
                        query.append(" AND g.genre = :genre");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    if (isFirst) {
                        query.append(" WHERE p.name = :pname");
                    } else {
                        query.append(" AND p.name = :pname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    //todo: actor filtering
                }
            }

            q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());

            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    q.setParameter("mtitle", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    q.setParameter("genre", filterInfo.getFilterData());
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    q.setParameter("pname", filterInfo.getFilterData());
                }

                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    //todo: actor filtering parameter
                }
            }
            records = q.list().size();
        } else {
            logger.debug("FilterInfoList isnull or empty! Get total number of records.");
            records = getNoOfRecords();
        }

        //    int records = DataAccessUtils.intResult(hibernateTemplate.find(query.toString()));
        logger.debug("Got total number of FILTERED records: " + records);
        return records;
    }
}
