package pl.radek.dvd.dto.raports;

import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.dto.PaginatedRaportList;

import java.util.List;

/**
 * Created by Sola on 2014-12-12.
 */
public class PaginatedListTopHits implements PaginatedRaportList<TopHitsDto> {
    private List<TopHitsDto> dtoList;
    private int noOfRecords;
    private int totalRecords;

    @Override
    public List<TopHitsDto> getDataList() {
        return dtoList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setDtoList(List<TopHitsDto> dtoList) {
        this.dtoList = dtoList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalRecords() {
        return totalRecords;
    }
}
