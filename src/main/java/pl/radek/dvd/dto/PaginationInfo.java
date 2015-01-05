package pl.radek.dvd.dto;

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
