package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.model.Constants;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class MovieNotReturnedFilterChoice extends MultiFiltreChoice {
    private static Logger logger = Logger.getLogger(MovieNotReturnedFilterChoice.class);

    public MovieNotReturnedFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
        StringBuilder query = new StringBuilder(" SELECT NEW pl.radek.dvd.dto.raports.MovieNotReturnedDto(c.id, c.firstName, c.lastName, m.title, rr.rentDate, rr.returnDate, DATEDIFF(:now, rr.returnDate), c.phoneNumber) FROM RentingRegistry as rr ");
        query.append("LEFT JOIN rr.client as c ");
        query.append("LEFT JOIN rr.movieCopy as mc ");
        query.append("LEFT JOIN mc.movie as m ");
        query.append("LEFT JOIN rr.receipt as r ");
        query.append("WHERE r.payDate IS NULL ");

        boolean isFirst = false;
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();
        buildFiltreQuery(filterInfoList, query, isFirst);

        query.append("ORDER BY DATEDIFF(:now, rr.returnDate) DESC ");

        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        int page = paginationInfo.getPage();
        int recordsPerPage = paginationInfo.getRecordsPerPage();
        int offset = (page - 1) * recordsPerPage;

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        setFiltreQueryParams(filterInfoList, q);

        q.setParameter("now", new Date());
        q.setFirstResult(offset);
        q.setMaxResults(recordsPerPage);

        return q;
    }

    @Override
    public int getNoOfRecords() {
        StringBuilder query = new StringBuilder(" SELECT COUNT(*) FROM RentingRegistry as rr ");
        query.append("LEFT JOIN rr.client as c ");
        query.append("LEFT JOIN rr.movieCopy as mc ");
        query.append("LEFT JOIN mc.movie as m ");
        query.append("LEFT JOIN rr.receipt as r ");
        query.append("WHERE r.payDate IS NULL ");

        boolean isFirst = false;
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();
        buildFiltreQuery(filterInfoList, query, isFirst);

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        setFiltreQueryParams(filterInfoList, q);

        int records;
        try {
            records = ((Number) q.iterate().next()).intValue();
        } catch (NoSuchElementException e) {
            return 0;
        }

        return records;
    }

    private void buildFiltreQuery(List<FilterInfo> filterInfoList, StringBuilder query, boolean isFirst) {
        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
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

                if (filterInfo.getFilterColumn().equals("date")) {
                    if (isFirst) {
                        query.append(" WHERE rr.rentDate BETWEEN :from AND :to ");
                    } else {
                        query.append(" AND rr.rentDate BETWEEN :from AND :to ");
                    }
                    isFirst = false;
                }
            }
        }
    }

    private void setFiltreQueryParams(List<FilterInfo> filterInfoList, Query q) {
        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals("date")) {
                    Map<String, Date> filterData = (Map<String, Date>) filterInfo.getFilterData();
                    q.setParameter("from", filterData.get("from"));
                    q.setParameter("to", filterData.get("to"));
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
}
