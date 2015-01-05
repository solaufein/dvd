package pl.radek.dvd.dto;

import java.util.List;

public interface PaginatedList<T> {
    List<T> getDataList();
    int getNoOfRecords();
}
