package com.jason.salaryApp.Utils;

import lombok.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public static String correctDate (String date) {
        String[] dateArray = StringUtils.convertDateString(date);
        String modifiedDate = "";
        int len = dateArray.length;
        if (len != 3) {
            System.out.println("这一天日期格式不对，省略扫描");
        }
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

    public static String getCurrentTime () {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return format.format(date).toString();
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
