package pl.radek.dvd.dto.movies;

import org.hibernate.validator.constraints.NotEmpty;
import pl.radek.dvd.model.Movie;
import pl.radek.dvd.model.RentingRegistry;

import java.util.Set;

public class MovieCopyDTO{
    private int id;

    @NotEmpty(message = "{NotEmpty.moviecopydto.serialNumber}")
    private String serialNumber;
    private String conditionInfo;
    private short availability;
    private Set<RentingRegistry> rentingRegistries;
    private Movie movie;

    public MovieCopyDTO() {
    }

    public MovieCopyDTO(int id, String serialNumber, String conditionInfo, short availability) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.conditionInfo = conditionInfo;
        this.availability = availability;
    }

    public MovieCopyDTO(int id, String serialNumber, String conditionInfo, short availability, Set<RentingRegistry> rentingRegistries, Movie movie) {
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
