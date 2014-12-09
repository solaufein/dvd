package pl.radek.dvd.logic.promotions;

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
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.promotions.PromotionData;
import pl.radek.dvd.model.Promotion;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:17
 */

@Repository
public class PromotionMySQLDAO implements PromotionDAO {

    private static Logger logger = Logger.getLogger(PromotionMySQLDAO.class);

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
    public List<Promotion> getPromotions() {
        logger.debug("Perform method getPromotions");

        String hql = "from Promotion";

        List<Promotion> promotions = hibernateTemplate.find(hql);

        return promotions;
    }

    @Override
    public List<Promotion> getPromotions(ListDataRequest listDataRequest) {
        logger.debug("Perform method getPromotions with listDataRequest");
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuilder query = new StringBuilder("FROM Promotion as p ");

        Query q = session.createQuery(query.toString());

        if (paginationInfo != null) {
            q.setFirstResult(offset);
            q.setMaxResults(recordsPerPage);
        }

        List<Promotion> promotions = (List<Promotion>) q.list();

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        /*for (Promotion promotion : promotions) {
            Hibernate.initialize(promotion.getMovies());
        }*/

        logger.debug("Got Paginated Promotions List ");

        return promotions;
    }

    @Override
    public Promotion getPromotion(int id) {
        logger.debug("Getting Promotion by id: " + id);

        //    Promotion promotion = (Promotion) hibernateTemplate.get(Promotion.class, id);


        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Promotion promotion = (Promotion) session.get(Promotion.class, id);

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        Hibernate.initialize(promotion.getMovies());

        logger.debug("Got Promotion by id: " + id);
        return promotion;
    }

    @Override
    public void deletePromotion(int id) {
        logger.debug("Deleting Promotion by id: " + id);

        hibernateTemplate.delete(hibernateTemplate.get(Promotion.class, id));
        //      hibernateTemplate.bulkUpdate("delete from Promotion where id = " + id);

        logger.debug("Deleted Promotion with id = " + id);
    }

    @Override
    public void addPromotion(Promotion promotion) {
        logger.info("add Promotion method begin");

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        session.save(promotion);

        //  hibernateTemplate.save(promotion);

        logger.info("add Promotion method END");
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        logger.info("update Promotion method begin");

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        session.update(promotion);

        //  hibernateTemplate.update(promotion);

        logger.info("update Promotion method END");
    }

    public int getNoOfRecords() {
        logger.debug("Getting total number of records");
        int records;
        String hql = "SELECT COUNT(*) FROM Promotion";

        records = DataAccessUtils.intResult(hibernateTemplate.find(hql));
        //      records = jdbcTemplate.queryForInt(hql);
        logger.debug("Got total number of records = " + records);

        return records;
    }

    @Override
    public int getNoOfRecords(ListDataRequest listDataRequest) {

        int records = getNoOfRecords();

        return records;
    }
}
