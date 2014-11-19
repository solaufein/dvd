package pl.radek.dvd.dto.movies;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

/**
 * Created by Sola on 2014-11-19.
 */
public class PaginatedListMoviesRent implements PaginatedList<MoviesRentData> {
    private List<MoviesRentData> moviesDatas;
    private int noOfRecords;

    @Override
    public List<MoviesRentData> getDataList() {
        return moviesDatas;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setMoviesDatas(List<MoviesRentData> moviesDatas) {
        this.moviesDatas = moviesDatas;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
