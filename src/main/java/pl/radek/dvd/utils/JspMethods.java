package pl.radek.dvd.utils;


import pl.radek.dvd.model.Constants;

public class JspMethods {

    /*public static String generateLink(String servlet, String order, String columnName, String field) {
        String link = null;

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            link = servlet +"?order=" + Constants.DESC + "&field="+ columnName;
        } else {
            link = servlet +"?order=" + Constants.ASC + "&field="+ columnName;
        }

        return link;
    }*/

    public static String generateLink(String order, String columnName, String field) {
        String link = null;

        if (Constants.ASC.equals(order) && columnName.equals(field)) {
            link = "?order=" + Constants.DESC + "&field=" + columnName;
        } else {
            link = "?order=" + Constants.ASC + "&field=" + columnName;
        }

        return link;
    }
}
