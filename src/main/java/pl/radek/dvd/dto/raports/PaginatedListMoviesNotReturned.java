package pl.radek.dvd.dto.raports;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

public class PaginatedListMoviesNotReturned implements PaginatedList<MovieNotReturnedDto> {
    private List<MovieNotReturnedDto> dtos;
    private int noOfRecords;

    @Override
    public List<MovieNotReturnedDto> getDataList() {
        return dtos;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setDtos(List<MovieNotReturnedDto> dtos) {
        this.dtos = dtos;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
