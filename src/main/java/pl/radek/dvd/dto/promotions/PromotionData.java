package pl.radek.dvd.dto.promotions;

import pl.radek.dvd.model.Movie;

import java.math.BigDecimal;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-07-26
 * Time: 16:06
 */
public class PromotionData {
    private int id;
    private String name;
    private BigDecimal value;
    private short promotionDaysNumber;
    private Set<Movie> movies;

    public PromotionData() {
    }

    public PromotionData(int id, String name, BigDecimal value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public PromotionData(int id, String name, BigDecimal value, short promotionDaysNumber, Set<Movie> movies) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.promotionDaysNumber = promotionDaysNumber;
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public short getPromotionDaysNumber() {
        return promotionDaysNumber;
    }

    public void setPromotionDaysNumber(short promotionDaysNumber) {
        this.promotionDaysNumber = promotionDaysNumber;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return name;
    }


}
