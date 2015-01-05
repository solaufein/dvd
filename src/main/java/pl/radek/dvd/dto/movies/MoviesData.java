package pl.radek.dvd.dto.movies;

import java.math.BigDecimal;

public class MoviesData {
    private int id;                        // movie id
    private String title;
    private String director;
    private String productionYear;
    private String genre;               // genre name
    private String name;               // promotion name
    private BigDecimal price;   // promotion value price

    public MoviesData() {
    }

    public MoviesData(String title, String director, String productionYear, String genre, String name, BigDecimal price) {
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.name = name;
        this.price = price;
    }

    public MoviesData(int id, String title, String director, String productionYear, String genre, String name, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.name = name;
        this.price = price;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
