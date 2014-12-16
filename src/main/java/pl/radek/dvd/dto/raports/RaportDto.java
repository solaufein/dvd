package pl.radek.dvd.dto.raports;

/**
 * Created by Sola on 2014-12-16.
 */
public class RaportDto {
    private Number periodName;
    private Number[] amount;

    public RaportDto() {
    }

    public RaportDto(Number periodName, Number[] amount) {
        this.periodName = periodName;
        this.amount = amount;
    }

    public Number getPeriodName() {
        return periodName;
    }

    public void setPeriodName(Number periodName) {
        this.periodName = periodName;
    }

    public Number[] getAmount() {
        return amount;
    }

    public void setAmount(Number[] amount) {
        this.amount = amount;
    }
}
