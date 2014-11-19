package pl.radek.dvd.dto.movies;

/**
 * Created by Sola on 2014-11-19.
 */
public class MoviesRentData {
    private int id;                        // movie copy id
    private String title;
    private String director;
    private String productionYear;
    private String genre;               // genre name
    private String name;               // promotion name
    private String movieCopySerial;

    public MoviesRentData() {
    }

    public MoviesRentData(int id, String title, String director, String productionYear, String genre, String name, String movieCopySerial) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.productionYear = productionYear;
        this.genre = genre;
        this.name = name;
        this.movieCopySerial = movieCopySerial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovieCopySerial() {
        return movieCopySerial;
    }

    public void setMovieCopySerial(String movieCopySerial) {
        this.movieCopySerial = movieCopySerial;
    }
}
