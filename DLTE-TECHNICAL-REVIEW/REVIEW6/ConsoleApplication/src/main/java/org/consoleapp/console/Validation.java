package org.consoleapp.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";//@ preceeded and followed by characters
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "(\\d{10})"; //only 10 digits
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public boolean isValidPin(int pin) {//pin can have only 6 digits
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
}
