package pl.radek.dvd.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * User: Sola
 * Date: 2014-09-24
 * Time: 15:19
 */
public class Encryption {
    public static String encrypt(String source) {
        String md5 = null;
        try {
            MessageDigest mdEnc = MessageDigest.getInstance("MD5"); // Encryption algorithm
            mdEnc.update(source.getBytes(), 0, source.length());
            md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted string
        } catch (Exception ex) {
            return null;
        }
        return md5;
    }
}