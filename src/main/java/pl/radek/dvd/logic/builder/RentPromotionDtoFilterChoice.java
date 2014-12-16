package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.model.Constants;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Sola on 2014-12-15.
 */
public class RentPromotionDtoFilterChoice extends MultiRaportFilterChoice {
    private static Logger logger = Logger.getLogger(RentPromotionDtoFilterChoice.class);

    /*SELECT
    MONTHNAME(rr.rent_date) AS period, p.name as 'promotion' , COUNT(rr.id) as 'count'
    FROM renting_registry as rr
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

    protected RentPromotionDtoFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
        return null;
    }

    @Override
    public int getNoOfRecords() {
        return 0;
    }

    @Override
    public int getTotalRecords() {
        return 0;
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
