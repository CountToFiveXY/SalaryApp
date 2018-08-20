package com.jason.salaryApp.Writer;

import com.jason.salaryApp.Utils.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {


    private final static String REPORT_NAME = "工资查询记录.txt";
    private final static String TITLE = "🌚功夫茶员工工资查询日志🌝";
    private final static String SEPARATOR = "========================";

    public static void writeLogs (String logs) {
        try {
            File reportFile = new File(REPORT_NAME);
            boolean isExisted = reportFile.exists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_NAME,true));

            if (!isExisted) {
                //Log的开头
                writer.write(TITLE);
                writer.newLine();
                writer.write(SEPARATOR);
                writer.newLine();
            }

            String[] logInfo = StringUtils.convertLogString(logs);

            for (String log : logInfo) {
                writer.write(log);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Some Thing Wrong Writing Report");
        }
    }
}
