package pl.radek.dvd.dto.movies;

/**
 * Created by Sola on 2014-12-02.
 */
public class MovieBySerialData {
    private int id;     // movie id
    private String title;   // movie title
    private String serialNumber;    // movie copy serial number

    public MovieBySerialData() {
    }

    public MovieBySerialData(int id, String title, String serialNumber) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
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

    public void setTitle(String title) {
        this.title = title;
    }
}
