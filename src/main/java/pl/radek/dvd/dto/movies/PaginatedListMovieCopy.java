package pl.radek.dvd.dto.movies;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-09-03
 * Time: 12:19
 */
public class PaginatedListMovieCopy implements PaginatedList<MovieCopyDTO> {
    private List<MovieCopyDTO> movieCopyDTOs;
    private int noOfRecords;

    @Override
    public List<MovieCopyDTO> getDataList() {
        return movieCopyDTOs;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setMovieCopyDTOs(List<MovieCopyDTO> movieCopyDTOs) {
        this.movieCopyDTOs = movieCopyDTOs;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
