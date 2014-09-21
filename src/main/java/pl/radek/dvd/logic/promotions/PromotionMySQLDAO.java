package pl.radek.dvd.logic.promotions;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
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
        hibernateTemplate.save(promotion);
    }

    @Override
    public void updatePromotion(Promotion promotion) {
        hibernateTemplate.update(promotion);
    }
}
