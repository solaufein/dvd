package pl.radek.dvd.dto;

import pl.radek.dvd.dto.FilterInfo;
import pl.radek.dvd.dto.PaginationInfo;
import pl.radek.dvd.dto.SortInfo;

import java.util.List;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:08
 */
public class ListDataRequest {
    private SortInfo sortInfo;
    private PaginationInfo paginationInfo;
    private List<FilterInfo> filterInfo;

    public ListDataRequest(SortInfo sortInfo, List<FilterInfo> filterInfo, PaginationInfo paginationInfo) {
        this.sortInfo = sortInfo;
        this.filterInfo = filterInfo;
        this.paginationInfo = paginationInfo;
    }

    public SortInfo getSortInfo() {
        return sortInfo;
    }

    public List<FilterInfo> getFilterInfo() {
        return filterInfo;
    }

    public PaginationInfo getPaginationInfo() {
        return paginationInfo;
    }
}
