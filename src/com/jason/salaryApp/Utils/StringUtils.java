package com.jason.salaryApp.Utils;

public class StringUtils {
    public static int toInteger(String s){
        return Integer.parseInt(s);
    }

    public static String combine(String str1, String str2) {
        return str1 + str2;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isFloat(String str) {
        if (isBlank(str)) {
            return false;
        }
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException exc) {
            return false;
        }
    }

    public static String[] convertCsvRowString(String s) {
        String[] result =  s.split(",");
        return result;
    }

    public static String[] convertDateString(String s) {
        String[] result = s.split("-");
        return result;
    }

    public static String[] convertWorkSlotString(String s) {
        String[] result = s.split("－");
        if (result.length != 2) {
            return s.split("-");
        }
        return result;
    }

    public static String[] convertWorkHourString(String s) {
        String[] result = s.split(":");
        if (result.length != 2) {
            return s.split("：");
        }
        return result;
    }

    public static String[] convertSalaryString(String s) {
        String[] result = s.split("-");
        return result;
    }

    public static String removeBlankPrefix(String s) {
        if (isBlank(s)) {
            return null;
        }
        while (s.startsWith(" ")) {
           s = s.substring(1);
        }
        return s;
    }

    private static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
