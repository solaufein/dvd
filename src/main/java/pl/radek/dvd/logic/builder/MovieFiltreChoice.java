package pl.radek.dvd.logic.builder;

import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.model.Constants;
import pl.radek.dvd.model.Movie;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-29
 * Time: 12:48
 */
public class MovieFiltreChoice extends MultiFiltreChoice {
    protected MovieFiltreChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
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
        query.append("LEFT JOIN m.actorset as a ");

        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);

        query.append(" GROUP BY m.title");

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

      //  Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            setFiltreQueryParams(filterInfoList, q);
        }

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
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");
        query.append("LEFT JOIN m.actorset as a ");

        Query q;
        int records;
        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);

        query.append(" GROUP BY m.title");

     //   q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());
        q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            setFiltreQueryParams(filterInfoList, q);
        }

        records = q.list().size();
        return records;
    }

    private void setFiltreQueryParams(List<FilterInfo> filterInfoList, Query q) {
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

    private void buildFiltreQuery(List<FilterInfo> filterInfoList, StringBuilder query, boolean isFirst) {
        if (filterInfoList != null && !filterInfoList.isEmpty()) {
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
                    if (isFirst) {
                        query.append(" WHERE CONCAT(a.firstName, ' ', a.lastName) LIKE :afull");
                    } else {
                        query.append(" AND CONCAT(a.firstName, ' ', a.lastName) LIKE :afull");
                    }
                    isFirst = false;
                }
            }
        }
    }
}
