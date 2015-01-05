package pl.radek.dvd.dto.clients;

import pl.radek.dvd.dto.PaginatedList;

import java.util.List;

public class PaginatedListClientDetails implements PaginatedList<ClientDetails> {
    private List<ClientDetails> dataList;
    private int noOfRecords;

    @Override
    public List<ClientDetails> getDataList() {
        return dataList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setDataList(List<ClientDetails> dataList) {
        this.dataList = dataList;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
}
