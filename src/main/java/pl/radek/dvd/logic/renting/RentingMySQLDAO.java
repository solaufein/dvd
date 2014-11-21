package pl.radek.dvd.logic.renting;

import org.apache.log4j.Logger;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.model.*;

import java.util.Date;

@Repository
public class RentingMySQLDAO implements RentingDAO {

    private static Logger logger = Logger.getLogger(RentingMySQLDAO.class);

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
    public int saveRentingRegistry(Employee employee, Client client, MovieCopy movieCopy, Date returnDate, Receipt receipt) {
        logger.debug("Saving Renting Registry");

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        Date rentDate = new Date();
        RentingRegistry rentingRegistry = new RentingRegistry();
        rentingRegistry.setRentDate(rentDate);
        rentingRegistry.setReturnDate(returnDate);
        rentingRegistry.setEmployee(employee);
        rentingRegistry.setClient(client);
        rentingRegistry.setMovieCopy(movieCopy);
        rentingRegistry.setReceipt(receipt);

        session.save(rentingRegistry);

        int rentingRegistryId = rentingRegistry.getId();

        //   logger.info("RR ID === " + rentingRegistryId);

        //   employee.getRentingRegistries().add(rentingRegistry);
        //   client.getRentingRegistries().add(rentingRegistry);
        //   movieCopy.getRentingRegistries().add(rentingRegistry);
        //   receipt.getRentingRegistries().add(rentingRegistry);

        logger.debug("Saved Renting Registry Successfully");
        return rentingRegistryId;
    }
}
