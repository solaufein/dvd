package pl.radek.dvd.dto.movies;

/**
 * User: Sola
 * Date: 2014-07-14
 * Time: 15:34
 */
public class MoviesData {
    private int id;                        // movie id
    private String title;
    private String director;
    private String productionYear;
    private String genre;               // genre name
    private String name;               // promotion name

    public MoviesData() {
    }

    public MoviesData(String title, String director, String productionYear, String genre, String name) {
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.name = name;
    }

    public MoviesData(int id, String title, String director, String productionYear, String genre, String name) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.name = name;
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
}
