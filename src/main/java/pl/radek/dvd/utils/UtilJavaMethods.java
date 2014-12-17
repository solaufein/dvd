package pl.radek.dvd.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Sola on 2014-11-21.
 */
public class UtilJavaMethods {
    public static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    static Random random = new SecureRandom();
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public static String randomString(char[] characterSet, int length) {

        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length);
            result[i] = characterSet[randomCharIndex];
        }
        return new String(result);
    }

    public static String securedRandomString() {
        String kod = new BigInteger(130, random).toString(32);

        return kod;
    }

    public static String formatDate(String pattern, Date date) {
       //    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, LocaleContextHolder.getLocale());
        simpleDateFormat.applyPattern(pattern);
        String formattedDate = simpleDateFormat.format(date);

        return formattedDate;
    }
}
