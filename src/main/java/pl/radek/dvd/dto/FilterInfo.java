package pl.radek.dvd.dto;

public class FilterInfo {
    private String filterColumn;
    private Object filterData;

    public FilterInfo(String filterColumn, Object filterData) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public Object getFilterData() {
        return filterData;
    }
}
