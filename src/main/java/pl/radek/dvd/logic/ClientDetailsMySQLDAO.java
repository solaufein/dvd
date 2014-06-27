package pl.radek.dvd.logic;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.model.Constants;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-06-02
 * Time: 17:25
 */

@Repository
@Transactional
public class ClientDetailsMySQLDAO implements ClientDetailsDAO {

    private static Logger logger = Logger.getLogger(ClientDetailsMySQLDAO.class);

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
    @SuppressWarnings("unchecked")
    public List<ClientDetails> getClientDetails(ListDataRequest listDataRequest, int id) {
        logger.debug("Perform method getClientDetails for Client id: " + id);

        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.ClientDetails(rr.id, m.title, mc.serialNumber, rr.rentDate, rr.returnDate) FROM RentingRegistry as rr ");
        query.append("INNER JOIN rr.client as c ");
        query.append("INNER JOIN rr.movieCopy as mc ");
        query.append("INNER JOIN mc.movie as m ");
        query.append("WHERE c.id = :ide ");
        query.append("ORDER BY rr.rentDate DESC ");

        Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());
        q.setParameter("ide", id);
        q.setFirstResult(offset);
        q.setMaxResults(recordsPerPage);

        logger.debug("query: " + query.toString());
        logger.debug("Got Client Details for Client id: " + id);
        List<ClientDetails> list = (List<ClientDetails>) q.list();

        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ReceiptPdf getReceiptPdfInformations(int id) {
        logger.debug("Perform method getReceiptPdfInformations for RR id: " + id);

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.ReceiptPdf(m.title, mc.serialNumber, rr.rentDate, rr.returnDate, r.price, r.payDate, r.billNumber) FROM RentingRegistry as rr ");
        query.append("INNER JOIN rr.receipt as r ");
        query.append("INNER JOIN rr.client as c ");
        query.append("INNER JOIN rr.movieCopy as mc ");
        query.append("INNER JOIN mc.movie as m ");
        query.append("WHERE rr.id = :ide ");

        Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(query.toString());
        q.setParameter("ide", id);

        logger.debug("query: " + query.toString());
        logger.debug("Got Receipt info for Client id: " + id);
        List<ReceiptPdf> list = (List<ReceiptPdf>) q.list();

        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public int getNoOfRecords(int clientId) {
        logger.debug("Getting total number of records for ClientDetails");
        int records;
        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM RentingRegistry as rr ");
        query.append("INNER JOIN rr.client as c ");
        query.append("WHERE c.id = ").append(clientId);

        records = DataAccessUtils.intResult(hibernateTemplate.find(query.toString()));
        //      records = jdbcTemplate.queryForInt(hql);
        logger.debug("Got total number of records for ClientDetails = " + records);

        return records;
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest, int clientId) {

        return getNoOfRecords(clientId);
    }

}
