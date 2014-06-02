package pl.radek.dvd.dto;

import java.util.List;

/**
 * User: Sola
 * Date: 2014-06-02
 * Time: 18:08
 */
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
