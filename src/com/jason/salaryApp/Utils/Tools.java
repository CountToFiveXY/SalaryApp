package com.jason.salaryApp.Utils;

import com.jason.salaryApp.Exceptions.WrongDateFormatException;
import lombok.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static final String LOG_SEPARATOR = "@";
    public static final String NEW_LINE = System.getProperty("line.separator");

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static String getLocalTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date()).substring(0,8);
    }

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(new Date());
    }

    public static String formatDate(String dateString) {
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new WrongDateFormatException(ErrorMessages.WRONG_INPUT_DATE_FORMAT + dateString);
        }
        return dateFormat.format(date);
    }

    public static boolean isBetweenTwoDays (String date, String start, String end) {
        Date startDate, endDate, workDate;
        try {
            workDate = dateFormat.parse(date);
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
        } catch (ParseException e) {
            throw new WrongDateFormatException(ErrorMessages.WRONG_INPUT_DATE_FORMAT);
        }
        return !(workDate.before(startDate) || workDate.after(endDate));
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void checkArgument(boolean condition, @NonNull String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }
}
