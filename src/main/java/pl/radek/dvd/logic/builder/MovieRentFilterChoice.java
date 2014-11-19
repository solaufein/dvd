package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.model.Constants;

import java.util.List;

/**
 * Created by Sola on 2014-11-19.
 */
public class MovieRentFilterChoice extends MultiFiltreChoice {
    private static Logger logger = Logger.getLogger(MovieRentFilterChoice.class);

    protected MovieRentFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    /*  MySQL query
        SELECT mc.id, movie.title, movie.director, movie.production_year, g.genre, p.name, mc.serial_number
        FROM movie
        LEFT JOIN movie_copy as mc ON mc.movie_id = movie.id
        LEFT JOIN genre as g ON g.id = movie.genre_id
        LEFT JOIN promotion as p ON p.id = movie.promotion_id
        LEFT JOIN starring as s ON movie.id = s.movie_id
        LEFT JOIN actors as a ON s.actors_id = a.id
        WHERE mc.availability = 1
        GROUP BY mc.id
        ORDER BY mc.serial_number ASC;
        */

    @Override
    public Query filtreQuery() {
        SortInfo sortInfo = listDataRequest.getSortInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.movies.MoviesRentData(mc.id , m.title, m.director, m.productionYear, g.genre, p.name, mc.serialNumber) FROM Movie as m ");
        query.append("LEFT JOIN m.movieCopies as mc ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");
        query.append("LEFT JOIN m.actorset as a ");

        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);

        query.append(" GROUP BY mc.id");

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

        String readyQuery = query.toString();
        logger.info("Ready Query = " + readyQuery);

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(readyQuery);

        setFiltreQueryParams(filterInfoList, q);

        if (paginationInfo != null) {
            q.setFirstResult(offset);
            q.setMaxResults(recordsPerPage);
        }
        return q;
    }

    @Override
    public int getNoOfRecords() {
        //SELECT COUNT(*) FROM Movie WHERE first_name LIKE 'J%' AND pesel LIKE '83%';
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Movie m ");
        query.append("LEFT JOIN m.movieCopies as mc ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");
        query.append("LEFT JOIN m.actorset as a ");

        Query q;
        int records;
        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);

        query.append(" GROUP BY mc.id");

        String readyQuery = query.toString();
        logger.info("Query No of Records = " + readyQuery);

        q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(readyQuery);

        setFiltreQueryParams(filterInfoList, q);

        records = q.list().size();
        return records;
    }

    private void setFiltreQueryParams(List<FilterInfo> filterInfoList, Query q) {

        short val = 1;
        q.setParameter("avail", val);

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    q.setParameter("afull", "%" + filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    q.setParameter("mtitle", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    q.setParameter("genre", filterInfo.getFilterData());
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    q.setParameter("pname", filterInfo.getFilterData());
                }
            }
        }
    }

    private void buildFiltreQuery(List<FilterInfo> filterInfoList, StringBuilder query, boolean isFirst) {

        query.append(" WHERE mc.availability = :avail");

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.TITLE)) {
                    query.append(" AND m.title LIKE :mtitle");
                }

                if (filterInfo.getFilterColumn().equals(Constants.GENRE)) {
                    query.append(" AND g.genre = :genre");
                }

                if (filterInfo.getFilterColumn().equals(Constants.PROMOTION)) {
                    query.append(" AND p.name = :pname");
                }

                if (filterInfo.getFilterColumn().equals(Constants.ACTOR)) {
                    query.append(" AND CONCAT(a.firstName, ' ', a.lastName) LIKE :afull");
                }
            }
        }
    }
}
