package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 15:01
 */

@Entity
@Table(name = "renting_registry")
public class RentingRegistry {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "rent_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentDate;

    @Column(name = "return_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @Column(name = "comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_copy_id", nullable = false)
    private MovieCopy movieCopy;

    public RentingRegistry() {
    }

    public RentingRegistry(int id, Date rentDate, Date returnDate, String comment, Client client, Employee employee, Receipt receipt, MovieCopy movieCopy) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.comment = comment;
        this.client = client;
        this.employee = employee;
        this.receipt = receipt;
        this.movieCopy = movieCopy;
    }

    public int getId() {
        return id;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getComment() {
        return comment;
    }

    public Client getClient() {
        return client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public MovieCopy getMovieCopy() {
        return movieCopy;
    }

    public void setMovieCopy(MovieCopy movieCopy) {
        this.movieCopy = movieCopy;
    }
}
