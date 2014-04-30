package pl.radek.dvd.logic;


import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;
import pl.radek.dvd.model.*;

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
@Transactional
public class ClientsMySQLDAO implements ClientsDAO {

    private static Logger logger = Logger.getLogger(ClientsMySQLDAO.class);

    private JdbcTemplate jdbcTemplate;
    private HibernateTemplate hibernateTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("unchecked")
    public List<Client> getClients() {
        logger.debug("Perform method getClients");

        String hql = "from Client";

        List<Client> clients = hibernateTemplate.find(hql);

        return clients;
    }

    @Override
    public List<Client> getClients(ListDataRequest listDataRequest) {
        logger.debug("Perform method getClients");

        final String hql = "FROM Client";
        List<Client> clients;
        SortInfo sortInfo = listDataRequest.getSortInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;


        if (sortInfo == null) {
            clients = hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Client.class), offset, recordsPerPage);
        } else {
            String field = sortInfo.getOrderField();
            boolean isAsc = sortInfo.isAsc();
            String order;

            if (isAsc == true) {
                order = Constants.ASC;
            } else {
                order = Constants.DESC;
            }
            final String newHql = hql + " ORDER BY " + field + " " + order;
            clients = hibernateTemplate.executeFind(new HibernateCallback<List<Client>>() {
                @Override
                public List<Client> doInHibernate(Session session) throws HibernateException, SQLException {
                    return session.createQuery(newHql).setFirstResult(offset).setMaxResults(recordsPerPage).list();
                }
            });
        }

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
    public void addClient(Client client) {
        String first_name = client.getFirstName();
        String last_name = client.getLastName();
        String pesel = client.getPesel();
        String city = client.getCity();
        String street = client.getStreet();
        String phone_number = client.getPhoneNumber();
        String email = client.getEmail();

        logger.debug("Adding client to db: firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);

        hibernateTemplate.save(client);

        logger.debug("Added client to db: firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);
    }

    @Override
    public void updateClient(Client client) {
        String first_name = client.getFirstName();
        String last_name = client.getLastName();
        String pesel = client.getPesel();
        String city = client.getCity();
        String street = client.getStreet();
        String phone_number = client.getPhoneNumber();
        String email = client.getEmail();
        int id = client.getId();

        logger.debug("Updating client with id =" + id + ", SETTING firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);

        hibernateTemplate.update(client);

        logger.debug("Updated client with id =" + id + ", SET firstname=" + first_name + ", lastname=" + last_name + ", pesel=" + pesel + ", city=" + city + ", street=" + street + ", phonenumber=" + phone_number + ", email=" + email);
    }

    @Override
    @SuppressWarnings("unchecked")
    public int getNoOfRecords() {
        logger.debug("Getting total number of records");
        int records;
        String hql = "SELECT COUNT(*) FROM Client";

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
