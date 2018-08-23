package com.jason.salaryApp.Writer;

import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    private final static String REPORT_NAME = "工资查询记录.txt";
    private final static String TITLE = "🌚功夫茶员工工资查询日志🌝";
    private final static String LOG_PREFIX = "本次查询时间为:";
    private final static String SEPARATOR = "========================";

    public static void writeLogs (String logs) {
        try {
            File reportFile = new File(REPORT_NAME);
            boolean isExisted = reportFile.exists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_NAME,true));

            //标题
            if (!isExisted) {
                writeNewLine(writer, TITLE);
            }

            String[] logInfo = StringUtils.convertLogString(logs);

            writeNewLine(writer, SEPARATOR);
            writeNewLine(writer, LOG_PREFIX + Tools.getCurrentTime());
            for (String log : logInfo) {
                writeNewLine(writer, log);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Some Thing Wrong Writing Report");
        }
    }

    private static void writeNewLine(BufferedWriter writer, String log) {
        try {
            writer.write(log + Tools.NEW_LINE);
        } catch (IOException e) {
            System.out.println("Some Thing Wrong Writing Report");
        }
    }
}
