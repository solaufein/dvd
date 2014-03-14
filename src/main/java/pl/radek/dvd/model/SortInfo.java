package pl.radek.dvd.model;

/**
 * User: Sola
 * Date: 14.03.14
 * Time: 18:03
 */
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
