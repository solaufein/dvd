package pl.radek.dvd.dto.raports;

import java.math.BigDecimal;

/**
 * Created by Sola on 2014-12-17.
 */
public class AmountPerPromotionRaport implements AmountPerX {
    private String name;
    private Number[] amount;
    private String totalAmount;

    public AmountPerPromotionRaport() {
    }

    public AmountPerPromotionRaport(String name, Number[] amount) {
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

    /*@Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append(" promotion name = " + promotionName);
        for (Number number : amount) {
            stringBuilder.append(", amount = " + number);
        }
        stringBuilder.append(" total = " + getTotalAmount());

        return stringBuilder.toString();
    }*/

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
