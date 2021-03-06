package pl.radek.dvd.logic;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.model.Roles;

import java.util.List;

@Repository
public class RoleMySQLDAO implements RoleDAO {

    private static Logger logger = Logger.getLogger(RoleMySQLDAO.class);

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
    public List<Roles> getRoles() {
        logger.debug("Perform method getRoles DAO");

        String hql = "from Roles";

        List<Roles> roles = hibernateTemplate.find(hql);

        return roles;
    }

    @Override
    public Roles getRole(int id) {
        logger.info("Getting role by id: " + id);

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Roles roles = (Roles) session.get(Roles.class, id);

        // must initialize - becouse entities are LAZY initialized and throw exception - proxy no session!
        Hibernate.initialize(roles.getEmployees());

        logger.info("Got role by id: " + id);
        return roles;
    }

    @Override
    public void deleteRole(int id) {
        logger.debug("Deleting role by id: " + id);

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();

        Roles r = (Roles) session.get(Roles.class, id);
        session.delete(r);

        logger.debug("Deleted role.");
    }
}
