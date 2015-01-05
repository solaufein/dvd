package pl.radek.dvd.dto;

public class SortInfo {
    private String orderField;
    private boolean asc;

    public SortInfo(String orderField, boolean asc) {
        this.orderField = orderField;
        this.asc = asc;
    }

    public String getOrderField() {
        return orderField;
    }

    public boolean isAsc() {
        return asc;
    }
}
