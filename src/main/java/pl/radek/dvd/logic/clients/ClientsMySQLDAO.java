package pl.radek.dvd.logic.clients;


import org.apache.log4j.Logger;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.*;
import pl.radek.dvd.dto.clients.ClientRentDto;
import pl.radek.dvd.exceptions.client.ClientNotFoundException;
import pl.radek.dvd.model.*;

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
        List<Client> clients = null;
        SortInfo sortInfo = listDataRequest.getSortInfo();
        PaginationInfo paginationInfo = listDataRequest.getPaginationInfo();
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        final int page = paginationInfo.getPage();
        final int recordsPerPage = paginationInfo.getRecordsPerPage();
        final int offset = (page - 1) * recordsPerPage;

        StringBuilder query = new StringBuilder("from Client c ");
        boolean isFirst = true;

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.FIRSTNAME)) {
                    if (isFirst) {
                        query.append(" WHERE c.firstName LIKE :fname");
                    } else {
                        query.append(" AND c.firstName LIKE :fname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.LASTNAME)) {
                    if (isFirst) {
                        query.append(" WHERE c.lastName LIKE :lname");
                    } else {
                        query.append(" AND c.lastName LIKE :lname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.PESEL)) {
                    if (isFirst) {
                        query.append(" WHERE c.pesel LIKE :pes");
                    } else {
                        query.append(" AND c.pesel LIKE :pes");
                    }
                    isFirst = false;
                }
            }
        }

        if (sortInfo != null) {
            String field = sortInfo.getOrderField();
            boolean isAsc = sortInfo.isAsc();
            String order;

            if (isAsc == true) {
                order = Constants.ASC;
            } else {
                order = Constants.DESC;
            }

            query.append(" ORDER BY " + field + " " + order);
        }

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.FIRSTNAME)) {
                    q.setParameter("fname", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.LASTNAME)) {
                    q.setParameter("lname", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.PESEL)) {
                    q.setParameter("pes", filterInfo.getFilterData() + "%");
                }
            }
        }

        if (paginationInfo != null) {
            q.setFirstResult(offset);
            q.setMaxResults(recordsPerPage);
        }

        logger.debug("Got sorted and filtered clients list from db");
        return q.list();
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
        logger.debug("Getting total number of FILTERED records");
        //SELECT COUNT(*) FROM Client WHERE first_name LIKE 'J%' AND pesel LIKE '83%';
        List<FilterInfo> filterInfoList = listDataRequest.getFilterInfo();

        StringBuilder query = new StringBuilder("SELECT COUNT(*) FROM Client c ");
        Query q;
        int records;
        boolean isFirst = true;

        if (filterInfoList != null && !filterInfoList.isEmpty()) {
            logger.debug("FilterInfoList is not null and not empty!");
            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.FIRSTNAME)) {
                    if (isFirst) {
                        query.append(" WHERE c.firstName LIKE :fname");
                    } else {
                        query.append(" AND c.firstName LIKE :fname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.LASTNAME)) {
                    if (isFirst) {
                        query.append(" WHERE c.lastName LIKE :lname");
                    } else {
                        query.append(" AND c.lastName LIKE :lname");
                    }
                    isFirst = false;
                }

                if (filterInfo.getFilterColumn().equals(Constants.PESEL)) {
                    if (isFirst) {
                        query.append(" WHERE c.pesel LIKE :pes");
                    } else {
                        query.append(" AND c.pesel LIKE :pes");
                    }
                    isFirst = false;
                }
            }

            q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());

            for (FilterInfo filterInfo : filterInfoList) {
                if (filterInfo.getFilterColumn().equals(Constants.FIRSTNAME)) {
                    q.setParameter("fname", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.LASTNAME)) {
                    q.setParameter("lname", filterInfo.getFilterData() + "%");
                }

                if (filterInfo.getFilterColumn().equals(Constants.PESEL)) {
                    q.setParameter("pes", filterInfo.getFilterData() + "%");
                }
            }
            records = q.list().size();
        } else {
            logger.debug("FilterInfoList isnull or empty! Get total number of records.");
            records = getNoOfRecords();
        }

        //    int records = DataAccessUtils.intResult(hibernateTemplate.find(query.toString()));
        logger.debug("Got total number of FILTERED records: " + records);
        return records;
    }

    @Override
    public List<ClientRentDto> getClients(String pesel) throws ClientNotFoundException {
        logger.info("Getting clients by pesel: " + pesel);

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.clients.ClientRentDto(c.id, c.firstName, c.lastName, c.pesel) FROM Client as c ");
        query.append("WHERE c.pesel LIKE :pesel ");
        query.append("ORDER BY c.pesel DESC ");

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());
        q.setParameter("pesel", pesel + "%");
        q.setFirstResult(0);
        q.setMaxResults(5);

        List<ClientRentDto> results = (List<ClientRentDto>) q.list();

        if (results.size() > 0) {
            logger.info("Got clients by pesel: " + pesel);
        } else {
            logger.info("Result list is null ");
            throw new ClientNotFoundException("Client with given pesel = " + pesel + " - not found.");
        }
        return results;
    }

    @Override
    public ClientRentDto getClient(String pesel) {
        logger.info("Getting client by pesel: " + pesel);
        ClientRentDto clientRentDto = null;

        StringBuilder query = new StringBuilder("SELECT NEW pl.radek.dvd.dto.clients.ClientRentDto(c.id, c.firstName, c.lastName, c.pesel) FROM Client as c ");
        query.append("WHERE c.pesel = :pesel ");
        query.append("ORDER BY c.pesel DESC ");

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());
        q.setParameter("pesel", pesel);

        List<ClientRentDto> results = (List<ClientRentDto>) q.list();

        if (results.size() > 0) {
            logger.info("Got client by pesel: " + pesel);
            clientRentDto = results.get(0);
        } else {
            logger.info("Result list is null ");
            // can throw exception ...
        }

        return clientRentDto;
    }

    @Override
    public  List<Client> getClientByLastName(String lastName) throws ClientNotFoundException {
        logger.info("Getting clients by lastName: " + lastName);

        StringBuilder query = new StringBuilder("FROM Client as c ");
        query.append("WHERE c.lastName LIKE :lastName ");
        query.append("ORDER BY c.lastName DESC ");

        Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(query.toString());
        q.setParameter("lastName", lastName + "%");
        q.setFirstResult(0);
        q.setMaxResults(5);

        List<Client> results = (List<Client>) q.list();

        if (results.size() > 0) {
            logger.info("Got clients by lastName: " + lastName);
        } else {
            logger.info("Result list is null ");
            throw new ClientNotFoundException("Client with given lastName = " + lastName + " - not found.");
        }
        return results;
    }
}
