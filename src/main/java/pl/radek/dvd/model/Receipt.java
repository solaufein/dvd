package pl.radek.dvd.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 15:48
 */

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pay_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payDate;

    @Column(name = "bill_number", nullable = false)
    private int billNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receipt")
    private Set<RentingRegistry> rentingRegistries = new HashSet<RentingRegistry>(0);

    public Receipt() {
    }

    public Receipt(int id, BigDecimal price, Date payDate, int billNumber) {
        this.id = id;
        this.price = price;
        this.payDate = payDate;
        this.billNumber = billNumber;
    }

    public Receipt(int id, BigDecimal price, Date payDate, int billNumber, Set<RentingRegistry> rentingRegistries) {
        this.id = id;
        this.price = price;
        this.payDate = payDate;
        this.billNumber = billNumber;
        this.rentingRegistries = rentingRegistries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public Set<RentingRegistry> getRentingRegistries() {
        return rentingRegistries;
    }

    public void setRentingRegistries(Set<RentingRegistry> rentingRegistries) {
        this.rentingRegistries = rentingRegistries;
    }
}
