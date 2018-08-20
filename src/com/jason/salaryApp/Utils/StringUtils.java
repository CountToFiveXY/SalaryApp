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

    public static String combine(String str1, String str2) {
        return str1 + str2;
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

    public static String[] convertWorkHourString(String s) {
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
        return s.split("@");
    }

    public static float convertTimeToNumberFormat(String timeString) {
        String[] timeArray = convertWorkHourString(timeString);
        if (timeArray.length > 1) {
            int hour = toInteger(timeArray[0]);
            float minute = toInteger(timeArray[1])/60f;
            return hour + minute;
        }
        return toFloat(timeArray[0]);
    }

    public static String correctDate (String date) {
        String[] dateArray = StringUtils.convertDateString(date);
        String modifiedDate = "";
        int len = dateArray.length;

        for(int i = 0; i < len; i ++){
            String s = dateArray[i];
            if (s.length() == 1){
                s = "0" + s;
            }
            if (i >= len-1) {
                modifiedDate += s;
            }else{
                modifiedDate += s + "-";
            }
        }
        return modifiedDate;
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

    private static int toInteger(String s){
        return Integer.parseInt(s);
    }
}
