package com.jason.salaryApp.Utils;

import com.jason.salaryApp.Exceptions.WrongDateFormatException;
import lombok.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static final String LOG_SEPARATOR = "@";

    public static String getLocalTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] timeArray = dateFormat.format(date).split("-");
        String year = timeArray[0];
        String month = timeArray[1];
        return year + "-" + month + "-";
    }

    public static boolean isBetweenTwoDays (String date, String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate, endDate, workDate;
        try {
            workDate = dateFormat.parse(date);
            startDate = dateFormat.parse(start);
            endDate = dateFormat.parse(end);
        } catch (ParseException e) {
            throw new WrongDateFormatException("Date format should be yyyy-MM-dd");
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
