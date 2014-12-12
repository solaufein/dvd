package pl.radek.dvd.dto;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:03
 */
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
