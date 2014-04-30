package pl.radek.dvd.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.model.Roles;

/**
 * User: Sola
 * Date: 2014-04-30
 * Time: 11:30
 */

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
    public Roles getRole(int id) {
        return hibernateTemplate.get(Roles.class, id);
    }
}
