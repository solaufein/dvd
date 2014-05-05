package pl.radek.dvd.logic;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.radek.dvd.dto.ListDataRequest;
import pl.radek.dvd.model.Employee;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-04-26
 * Time: 16:20
 */

@Repository
@Transactional
public class EmployeeMySQLDAO implements EmployeeDAO {

    private static Logger logger = Logger.getLogger(EmployeeMySQLDAO.class);

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
    public List<Employee> getEmployees() {
        logger.debug("Perform method getEmployees");

        String hql = "from Employee";

        List<Employee> employees = hibernateTemplate.find(hql);

        return employees;
    }

    @Override
    public List<Employee> getEmployees(ListDataRequest listDataRequest) {
        return null;
    }

    @Override
    public Employee getEmployee(int id) {
        logger.debug("Getting employee by id: " + id);

        Employee employee = (Employee) hibernateTemplate.get(Employee.class, id);

        logger.debug("Got employee by id: " + id);
        return employee;
    }

    @Override
    public Employee getEmployee(String login) {
        logger.debug("Getting employee by login(mail): " + login);

        String hql = "FROM Employee e WHERE e.email = :login";
        Query q = hibernateTemplate.getSessionFactory().openSession().createQuery(hql);
        q.setParameter("login", login);
        List results = q.list();

        if (results.size() > 0) {
            Employee employee = (Employee) results.get(0);
            logger.debug("Got employee by login(mail): " + login);
            return employee;
        } else {
            logger.debug("Result list is null ");
            return null;
        }
    }

    @Override
    public void deleteEmployee(int id) {
        logger.debug("Deleting employee by id: " + id);

        hibernateTemplate.delete(hibernateTemplate.get(Employee.class, id));
        //      hibernateTemplate.bulkUpdate("delete from Employee where id = " + id);

        logger.debug("Deleted employee");
    }

    @Override
    public void addEmployee(Employee employee) {
        String first_name = employee.getFirstName();
        String last_name = employee.getLastName();
        String phone_number = employee.getPhoneNumber();
        String email = employee.getEmail();
        String password = employee.getPassword();

        logger.debug("Adding employee to db: firstname=" + first_name + ", lastname=" + last_name + ", phonenumber=" + phone_number + ", email=" + email + ", password=" + password);

        hibernateTemplate.save(employee);

        logger.debug("Added employee to db: firstname=" + first_name + ", lastname=" + last_name + ", phonenumber=" + phone_number + ", email=" + email + ", password=" + password);
    }

    @Override
    public void updateEmployee(Employee employee) {
        String first_name = employee.getFirstName();
        String last_name = employee.getLastName();
        String phone_number = employee.getPhoneNumber();
        String email = employee.getEmail();
        String password = employee.getPassword();
        int id = employee.getId();

        logger.debug("Updating employee with id =" + id + ", SETTING firstname=" + first_name + ", lastname=" + last_name + ", phonenumber=" + phone_number + ", email=" + email + ", password=" + password);

        hibernateTemplate.update(employee);

        logger.debug("Updated employee with id =" + id + ", SETTING firstname=" + first_name + ", lastname=" + last_name + ", phonenumber=" + phone_number + ", email=" + email + ", password=" + password);

    }

    @Override
    public int getNoOfRecords() {
        logger.debug("Getting total number of records");
        int records;
        String hql = "SELECT COUNT(*) FROM Employee";

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
