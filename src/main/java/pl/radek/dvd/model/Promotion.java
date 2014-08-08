package pl.radek.dvd.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * User: Sola
 * Date: 2014-05-27
 * Time: 16:56
 */

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private BigDecimal value;

    @Column(name = "promotion_days_number", nullable = false)
    private short promotionDaysNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    private Set<Movie> movies = new HashSet<Movie>(0);

    public Promotion() {
    }

    public Promotion(int id, String name, BigDecimal value, short promotionDaysNumber) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.promotionDaysNumber = promotionDaysNumber;
    }

    public Promotion(int id, String name, BigDecimal value, short promotionDaysNumber, Set<Movie> movies) {
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
