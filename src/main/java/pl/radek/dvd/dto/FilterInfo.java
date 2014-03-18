package pl.radek.dvd.dto;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:03
 */
public class FilterInfo {
    private String filterColumn;
    private String filterData;

    public FilterInfo(String filterColumn, String filterData) {
        this.filterColumn = filterColumn;
        this.filterData = filterData;
    }

    public String getFilterColumn() {
        return filterColumn;
    }

    public String getFilterData() {
        return filterData;
    }
}
