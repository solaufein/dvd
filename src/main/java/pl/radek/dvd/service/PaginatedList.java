package pl.radek.dvd.service;

import java.util.List;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:01
 */
public interface PaginatedList<T> {
    List<T> getDataList();
    int getNoOfRecords();
}
