package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.model.Constants;

import java.util.List;
import java.util.NoSuchElementException;

public class MovieRentFilterChoice extends MultiFiltreChoice {
    private static Logger logger = Logger.getLogger(MovieRentFilterChoice.class);

    protected MovieRentFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
        SortInfo sortInfo = listDataRequest.getSortInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.movies.MoviesRentData(mc.id , m.title, m.director, m.productionYear, g.genre, p.name, mc.serialNumber, p.value) FROM Movie as m ");
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

            if (field.equals(Constants.PRICE)) {
                field = "value";
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
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        StringBuilder query = new StringBuilder("SELECT COUNT(distinct mc.id) FROM Movie m ");      // count(distinct mc.id) instead of group by mc.id
        query.append("LEFT JOIN m.movieCopies as mc ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");
        query.append("LEFT JOIN m.actorset as a ");

        Query q;
        int records;
        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);

        String readyQuery = query.toString();
        logger.info("Query No of Records = " + readyQuery);

        q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(readyQuery);

        setFiltreQueryParams(filterInfoList, q);

        try {
            records = ((Number) q.iterate().next()).intValue();
        } catch (NoSuchElementException e) {
            return 0;
        }

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
