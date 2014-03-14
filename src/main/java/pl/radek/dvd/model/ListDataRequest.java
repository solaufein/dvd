package pl.radek.dvd.model;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:08
 */
public class ListDataRequest {
    private SortInfo sortInfo;
    private PaginationInfo paginationInfo;
    private FilterInfo filterInfo;

    public ListDataRequest(SortInfo sortInfo, FilterInfo filterInfo, PaginationInfo paginationInfo) {
        this.sortInfo = sortInfo;
        this.filterInfo = filterInfo;
        this.paginationInfo = paginationInfo;
    }

    public SortInfo getSortInfo() {
        return sortInfo;
    }

    public FilterInfo getFilterInfo() {
        return filterInfo;
    }

    public PaginationInfo getPaginationInfo() {
        return paginationInfo;
    }
}
