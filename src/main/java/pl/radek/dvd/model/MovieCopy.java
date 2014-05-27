package pl.radek.dvd.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 15:58
 */

@Entity
@Table(name = "movie_copy")
public class MovieCopy {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "condition_info")
    private String conditionInfo;

    @Column(name = "availability", nullable = false)
    private short availability;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "movieCopy")
    private Set<RentingRegistry> rentingRegistries = new HashSet<RentingRegistry>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public MovieCopy() {
    }

    public MovieCopy(int id, String serialNumber, String conditionInfo, short availability) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.conditionInfo = conditionInfo;
        this.availability = availability;
    }

    public MovieCopy(int id, String serialNumber, String conditionInfo, short availability, Set<RentingRegistry> rentingRegistries, Movie movie) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.conditionInfo = conditionInfo;
        this.availability = availability;
        this.rentingRegistries = rentingRegistries;
        this.movie = movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getConditionInfo() {
        return conditionInfo;
    }

    public void setConditionInfo(String conditionInfo) {
        this.conditionInfo = conditionInfo;
    }

    public short getAvailability() {
        return availability;
    }

    public void setAvailability(short availability) {
        this.availability = availability;
    }

    public Set<RentingRegistry> getRentingRegistries() {
        return rentingRegistries;
    }

    public void setRentingRegistries(Set<RentingRegistry> rentingRegistries) {
        this.rentingRegistries = rentingRegistries;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
