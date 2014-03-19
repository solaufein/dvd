package pl.radek.dvd.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Sola
 * Date: 21.01.14
 * Time: 16:55
 * To change this template use File | Settings | File Templates.
 */
public class FormValidator {

    private Map<String, String> errors = new HashMap<String, String>();
    private Pattern regexPattern;
    private Matcher regMatcher;

    public void validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher   = regexPattern.matcher(emailAddress);
        if (!regMatcher.matches()) {
            errors.put("email"," - Invalid Email");
        }
    }

    public void validatePhoneNumber(String number) {
        regexPattern = Pattern.compile("[0-9()-\\.]*");
        regMatcher   = regexPattern.matcher(number);
        if (!regMatcher.matches()) {
            errors.put("phone_number"," - Invalid phone number");
        }
    }

    public void validatePesel(String pesel) {
        regexPattern = Pattern.compile("\\d{11}");
        regMatcher   = regexPattern.matcher(pesel);
        if (!regMatcher.matches()) {
            errors.put("pesel"," - Invalid Pesel");
        }
    }

    public void validateAlfabeticField(String field, String errorName, String errorMsg){
        regexPattern = Pattern.compile("^[A-Z][a-ząęśćńółżź]+$");
        regMatcher   = regexPattern.matcher(field);
        if (field.equals("") || !regMatcher.matches()){
            errors.put(errorName, errorMsg);
        }
    }

    public void validateEmptyField(String field, String errorName, String errorMsg){
        if (field.equals("")){
            errors.put(errorName, errorMsg);
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
