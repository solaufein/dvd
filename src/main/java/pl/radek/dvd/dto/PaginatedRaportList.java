package pl.radek.dvd.dto;

/**
 * Created by Sola on 2014-12-12.
 */
public interface PaginatedRaportList<T> extends PaginatedList<T> {
    int getTotalRecords();
}
