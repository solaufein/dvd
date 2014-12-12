package pl.radek.dvd.logic.builder;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;

import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Created by Sola on 2014-12-09.
 */
public class MovieNotReturnedFilterChoice extends MultiFiltreChoice {
    private static Logger logger = Logger.getLogger(MovieNotReturnedFilterChoice.class);

    public MovieNotReturnedFilterChoice(ListDataRequest listDataRequest, HibernateTemplate hibernateTemplate) {
        super(listDataRequest, hibernateTemplate);
    }

    @Override
    public Query filtreQuery() {
        /*SELECT c.first_name, c.last_name, m.title, rr.rent_date, rr.return_date, ABS(DATEDIFF(rr.return_date,r.pay_date)) as 'days late' ,c.phone_number
        FROM renting_registry as rr
        LEFT JOIN client as c ON c.id = rr.client_id
        LEFT JOIN movie_copy as mc ON rr.movie_copy_id = mc.id
        LEFT JOIN movie as m ON m.id = mc.movie_id
        LEFT JOIN receipt as r ON r.id = rr.receipt_id
        WHERE r.pay_date IS NULL
        ORDER BY ABS(DATEDIFF(rr.return_date,r.pay_date)) DESC
        LIMIT 0,9;*/

        StringBuilder query = new StringBuilder(" SELECT NEW pl.radek.dvd.dto.raports.MovieNotReturnedDto(c.firstName, c.lastName, m.title, rr.rentDate, rr.returnDate, DATEDIFF(:now, rr.returnDate), c.phoneNumber) FROM RentingRegistry as rr ");
        query.append("LEFT JOIN rr.client as c ");
        query.append("LEFT JOIN rr.movieCopy as mc ");
        query.append("LEFT JOIN mc.movie as m ");
        query.append("LEFT JOIN rr.receipt as r ");
        query.append("WHERE r.payDate IS NULL ");
        query.append("ORDER BY DATEDIFF(:now, rr.returnDate) DESC ");

        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        int page = paginationInfo.getPage();
        int recordsPerPage = paginationInfo.getRecordsPerPage();
        int offset = (page - 1) * recordsPerPage;

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());
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
        //    query.append("ORDER BY ABS(DATEDIFF(rr.returnDate,r.payDate)) DESC ");

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        int records;
        try {
            records = ((Number) q.iterate().next()).intValue();
        } catch (NoSuchElementException e) {
            return 0;
        }
        // int records = ((Number) q.uniqueResult()).intValue();
        //  int records = q.list().size();
        return records;
    }
}
