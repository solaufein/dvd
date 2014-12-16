package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.model.Constants;

import java.util.*;

/**
 * Created by Sola on 2014-12-15.
 */
public class IncomeDtoFilterChoice extends MultiRaportFilterChoice {
    private static Logger logger = Logger.getLogger(IncomeDtoFilterChoice.class);

    protected IncomeDtoFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    /*SELECT
    MONTHNAME(rr.rent_date) AS period, p.name as 'promotion' , SUM(r.price) as 'amount'
    FROM renting_registry as rr
    LEFT JOIN receipt as r ON r.id = rr.receipt_id
    LEFT JOIN movie_copy as mc ON mc.id = rr.movie_copy_id
    LEFT JOIN movie as m ON m.id = mc.movie_id
    LEFT JOIN promotion as p ON p.id = m.promotion_id
    LEFT JOIN genre as g ON g.id = m.genre_id
    WHERE g.id = 5 AND rr.rent_date BETWEEN '2013-05-01 00:00:00' AND '2013-12-01 00:00:00'
    GROUP BY MONTHNAME(rr.rent_date), p.id
    ORDER BY MONTHNAME(rr.rent_date) DESC LIMIT 0,9;*/

    /*UWAGA:
    w tym raporcie i w poniższym, gdzie sa przekroje, tylko zamieniamy zamiast MONTHNAME(x) na YEAR(x) - rocznie,
    ,WEEKDAY(x) - dzien tygodnia lub WEEK(x) - numer tygodnia i grupujemy odpowiednio po nich*/

    @Override
    public Query filtreQuery() {
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        int page = paginationInfo.getPage();
        int recordsPerPage = paginationInfo.getRecordsPerPage();
        int offset = (page - 1) * recordsPerPage;

        String period = getPeriod(filterInfoList);
        StringBuilder query = createQuery(period);

        boolean isFirst = true;

        buildFiltreQuery(filterInfoList, query, isFirst);
        setGroupByAndOrderToQuery(query, period, "ASC");

        String queryString = query.toString();
        logger.info("Income query = " + queryString);
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(queryString);

        setFiltreQueryParams(filterInfoList, q);

        q.setFirstResult(offset);
        q.setMaxResults(recordsPerPage);

        return q;
    }

    @Override
    public int getNoOfRecords() {
        int records;
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        String period = getPeriod(filterInfoList);
        StringBuilder query = createNoOfRecordsNativeQuery(period);

        boolean isFirst = true;

        buildFiltreNativeQuery(filterInfoList, query, isFirst);
        //    setGroupByAndOrderToQuery(query, period);

        String s = query.toString();
        logger.info("No Of Records Query: " + s);
        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(s);

        setFiltreQueryParams(filterInfoList, q);

        /*try {
            records = ((Number) q.iterate().next()).intValue();
        } catch (NoSuchElementException e) {
            return 0;
        }*/

        records = ((Number) q.uniqueResult()).intValue();

        //    records = q.list().size();

        return records;
    }

    @Override
    public int getTotalRecords() {
        return 10;
    }

    private StringBuilder createQuery(String period) {
        StringBuilder query = new StringBuilder(" SELECT NEW pl.radek.dvd.dto.raports.IncomePromotionDTO(");
        query.append(period);   // WEEK, MONTHNAME, YEAR, WEEKDAY
        query.append("(rr.rentDate), p.name, SUM(r.price)) FROM RentingRegistry as rr ");
        query.append("LEFT JOIN rr.receipt as r ");
        query.append("LEFT JOIN rr.movieCopy as mc ");
        query.append("LEFT JOIN mc.movie as m ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");

        return query;
    }

    private StringBuilder createNoOfRecordsQuery(String period) {
        /*StringBuilder query = new StringBuilder("SELECT COUNT(distinct p.id, ");
        query.append(period);
        query.append("(rr.rentDate)) FROM RentingRegistry as rr ");*/
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM RentingRegistry as rr ");
        query.append("LEFT JOIN rr.receipt as r ");
        query.append("LEFT JOIN rr.movieCopy as mc ");
        query.append("LEFT JOIN mc.movie as m ");
        query.append("LEFT JOIN m.genre as g ");
        query.append("LEFT JOIN m.promotion as p ");

        return query;
    }

    private StringBuilder createNoOfRecordsNativeQuery(String period) {
        //Native Query
        StringBuilder queryNative = new StringBuilder("SELECT COUNT(distinct p.id, ");
        queryNative.append(period);
        queryNative.append("(rr.rent_date)) FROM renting_registry as rr ");
        queryNative.append("LEFT JOIN receipt as r ON r.id = rr.receipt_id ");
        queryNative.append("LEFT JOIN movie_copy as mc ON mc.id = rr.movie_copy_id ");
        queryNative.append("LEFT JOIN movie as m ON m.id = mc.movie_id ");
        queryNative.append("LEFT JOIN genre as g ON g.id = m.genre_id ");
        queryNative.append("LEFT JOIN promotion as p ON p.id = m.promotion_id ");


        return queryNative;
    }

    private String getPeriod(List<FilterInfo> filterInfoList) {
        String period = null;

        for (FilterInfo filterInfo : filterInfoList) {
            if (filterInfo.getFilterColumn().equals(Constants.SECTION)) {
                Object filterData = filterInfo.getFilterData();
                if (filterData.equals("WEEK")) {
                    period = "WEEK";
                } else if (filterData.equals("YEAR")) {
                    period = "YEAR";
                } else if (filterData.equals("DAY")) {
                    period = "DAY";
                } else if (filterData.equals("MONTH")) {
                    period = "MONTH";
                }
            }
        }
        return period;
    }

    private void setGroupByAndOrderToQuery(StringBuilder query, String period, String order) {
        query.append(" GROUP BY ");
        query.append(period);
        query.append("(rr.rentDate), p.id ");

        query.append(" ORDER BY ");
        query.append(period);
        query.append("(rr.rentDate) ");
        query.append(order);
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

    private void buildFiltreNativeQuery(List<FilterInfo> filterInfoList, StringBuilder query, boolean isFirst) {
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

                if (filterInfo.getFilterColumn().equals("date")) {
                    if (isFirst) {
                        query.append(" WHERE rr.rent_date BETWEEN :from AND :to ");
                    } else {
                        query.append(" AND rr.rent_date BETWEEN :from AND :to ");
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
            }
        }
    }
}
