package pl.radek.dvd.dto.raports;

import java.math.BigDecimal;

public class AmountPerPeriodRaport implements AmountPerX {
    private String name;      // Number
    private Number[] amount;
    private String totalAmount;

    public AmountPerPeriodRaport() {
    }

    public AmountPerPeriodRaport(String name, Number[] amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number[] getAmount() {
        return amount;
    }

    public void setAmount(Number[] amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        BigDecimal bd = BigDecimal.ZERO;
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);

        for (Number number : amount) {
            if (number != null) {
                bd = bd.add(new BigDecimal(number.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
            }
        }
        String s = bd.toString();
        totalAmount = s;

        return totalAmount;
    }
}
