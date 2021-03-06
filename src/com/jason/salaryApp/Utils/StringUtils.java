package com.jason.salaryApp.Utils;

public class StringUtils {

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

    public static float toFloat(String s) {
        return Float.parseFloat(s);
    }

    public static String[] convertCsvRowString(String s) {
        return s.split(",");
    }

    public static String[] convertDateString(String s) {
        return s.split("-");
    }

    public static String[] convertWorkSlotString(String s) {
        String[] result = s.split("－");
        if (result.length != 2) {
            return s.split("-");
        }
        return result;
    }

    static String[] convertWorkHourString(String s) {
        String[] result = s.split(":");
        if (result.length != 2) {
            return s.split("：");
        }
        return result;
    }

    public static String[] convertSalaryString(String s) {
        return s.split("-");
    }

    public static String[] convertLogString(String s) {
        return s.split(Tools.LOG_SEPARATOR);
    }

    public static float convertTimeToNumberFormat(String timeString) {
        boolean isAm = false;
        if (timeString.endsWith("am") || timeString.startsWith("12")) {
            isAm = true;
        }
        String time = timeString.substring(0, timeString.length() - 2);
        String[] timeArray = convertWorkHourString(time);
        int hour = isAm? toInteger(timeArray[0]) : toInteger(timeArray[0]) + 12;
        if (timeArray.length > 1) {
            float minute = toInteger(timeArray[1])/60f;
            return hour + minute;
        }
        return hour;
    }

    public static String removeBlankPrefixAndSuffix(String s) {
        if (isBlank(s)) {
            return null;
        }
        while (s.startsWith(" ")) {
           s = s.substring(1);
        }
        while (s.endsWith(" ")) {
            s = s.substring(0, s.length()-1);
        }
        return s;
    }

    public static String replaceStashString(String s) {
        if (isBlank(s)) { return null; }
        return s.replace("－", "-");
    }

    public static boolean isFullTime(String s) {
        return s.startsWith("*");
    }

    public static boolean isBlank(String str) {
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

    public static int toInteger(String s){
        return Integer.parseInt(s);
    }
}
