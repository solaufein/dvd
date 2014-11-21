package pl.radek.dvd.logic.receipts;

import org.apache.log4j.Logger;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.model.Receipt;

@Repository
public class ReceiptMySQLDAO implements ReceiptDAO {
    private static Logger logger = Logger.getLogger(ReceiptMySQLDAO.class);

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
    public void deleteReceipt(int id) {
        logger.debug("Deleting Receipt by id: " + id);

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        Receipt receipt = (Receipt) session.get(Receipt.class, id);
        session.delete(receipt);

        logger.debug("Deleted Receipt");
    }

    @Override
    public void addReceipt(Receipt receipt) {
        logger.debug("Saving Receipt");

        hibernateTemplate.save(receipt);

        logger.debug("Saved Receipt");
    }

    @Override
    public void updateReceipt(Receipt receipt) {
        logger.debug("Updating Receipt");

        hibernateTemplate.update(receipt);

        logger.debug("Updated Receipt");
    }
}
