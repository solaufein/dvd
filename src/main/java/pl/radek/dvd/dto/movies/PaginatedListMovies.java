package pl.radek.dvd.dto.movies;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

public class PaginatedListMovies implements PaginatedList<MoviesData> {
    private List<MoviesData> moviesDatas;
    private int noOfRecords;

    @Override
    public List<MoviesData> getDataList() {
        return moviesDatas;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setMoviesDatas(List<MoviesData> moviesDatas) {
        this.moviesDatas = moviesDatas;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
