package pl.radek.dvd.dto;

import pl.radek.dvd.dto.PaginatedList;
import pl.radek.dvd.model.Client;

import java.util.List;

/**
 * User: Sola
 * Date: 15.03.14
 * Time: 16:29
 */
public class PaginatedListImpl implements PaginatedList<Client> {
    private List<Client> dataList;
    private int noOfRecords;

    @Override
    public List<Client> getDataList() {
        return dataList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setDataList(List<Client> dataList) {
        this.dataList = dataList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
