package pl.radek.dvd.logic;


import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import pl.radek.dvd.logic.daomapper.ClientRowMapper;
import pl.radek.dvd.model.Client;

import java.sql.SQLException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 13.01.14
 * Time: 15:50
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ClientsMySQLDAO implements ClientsDAO {

    private static Logger logger = Logger.getLogger(ClientsMySQLDAO.class);

    private JdbcTemplate jdbcTemplate;
    private HibernateTemplate hibernateTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

   /* public void setSessionFactory(SessionFactory sessionFactory) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }*/

    @SuppressWarnings("unchecked")
    public List<Client> getClients() {
        logger.debug("Perform method getClients");

        String hql = "from Client";

        List<Client> clients = hibernateTemplate.find(hql);

        return clients;
    }

    @Override
    public List<Client> getClientsByPage(int offset, int noOfRecords) {
        logger.debug("Perform method getClientsByPage");
        //test przez intellij 3

        String hql = "from Client";
        List<Client> clients = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Client.class), offset, noOfRecords);

        logger.debug("Got sorted clients list from db");
        return clients;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Client> getClientsSortedAndPaged(String field, String order, final int offset, final int noOfRecords) {
        logger.debug("Perform method getClientsSortedAndPaged");

        final String hql = "FROM Client ORDER BY " + field + " " + order;

        List<Client> clients = hibernateTemplate.executeFind(new HibernateCallback<List<Client>>() {
            @Override
            public List<Client> doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createQuery(hql).setFirstResult(offset).setMaxResults(noOfRecords).list();
            }
        });

        logger.debug("Got sorted clients list from db");
        return clients;
    }

    @Override
    public Client getClient(int id) {
        logger.debug("Getting client by id: " + id);

        Client client = (Client) hibernateTemplate.get(Client.class, id);

        logger.debug("Got client by id: " + id);
        return client;
    }

    @Override
    public void deleteClient(int id) {
        logger.debug("Deleting client by id: " + id);

        hibernateTemplate.delete(hibernateTemplate.get(Client.class, id));
        //      hibernateTemplate.bulkUpdate("delete from Client where id = " + id);

        logger.debug("Deleted client");
    }

    @Override
    public void addClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email) {
        logger.debug("Adding client to db: firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);

        hibernateTemplate.save(new Client(first_name, last_name, pesel, city, street, phone_number, email));

        logger.debug("Added client to db: firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);
    }

    @Override
    public void updateClient(String first_name, String last_name, String pesel, String city, String street, String phone_number, String email, int id) {
        logger.debug("Updating client with id =" + id + ", SETTING firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);

        hibernateTemplate.update(new Client(id, first_name, last_name, pesel, city, street, phone_number, email));

        logger.debug("Updated client with id =" + id + ", SET firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);
    }

    @Override
    public int getNoOfRecords() {
        logger.debug("Getting total number of records");
        int records;
        String hql = "SELECT COUNT(*) FROM Client";

        records = DataAccessUtils.intResult(hibernateTemplate.find(hql));
        //      records = jdbcTemplate.queryForInt(hql);
        logger.debug("Got total number of records = " + records);

        return records;
    }
}
