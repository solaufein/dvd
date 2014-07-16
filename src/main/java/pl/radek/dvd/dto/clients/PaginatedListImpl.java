package pl.radek.dvd.dto.clients;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

/**
 * User: Sola
 * Date: 15.03.14
 * Time: 16:29
 */
public class PaginatedListImpl implements PaginatedList<ClientData> {
    private List<ClientData> dataList;
    private int noOfRecords;

    @Override
    public List<ClientData> getDataList() {
        return dataList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setDataList(List<ClientData> dataList) {
        this.dataList = dataList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
