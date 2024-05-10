package com.example.aadhaar.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileAndStringUtils {

    public static  String removeSpaces(String input) {
        return input.replaceAll("\\s", "");
    }

    public static boolean isValidIndianMobileNumber(String mobileNumber) {
        String regex = "^\\+91[1-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobileNumber);
        return matcher.matches();
    }

}
