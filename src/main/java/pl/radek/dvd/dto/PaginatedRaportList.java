package pl.radek.dvd.dto;

public interface PaginatedRaportList<T> extends PaginatedList<T> {
    int getTotalRecords();
}
