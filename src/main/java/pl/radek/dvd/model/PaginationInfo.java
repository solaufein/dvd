package pl.radek.dvd.model;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:03
 */
public class PaginationInfo {
    private int page;
    private int recordsPerPage;

    public PaginationInfo(int page, int recordsPerPage) {
        this.page = page;
        this.recordsPerPage = recordsPerPage;
    }

    public int getPage() {
        return page;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }
}
