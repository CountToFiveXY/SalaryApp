package com.jason.salaryApp.Writer;

import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {

    private final static String REPORT_NAME = "工资查询记录.txt";
    private final static String REPORT_TITLE = "🌚功夫茶员工工资查询日志🌝";
    private final static String REPORT_PREFIX = "本次查询时间为:";
    private final static String SEPARATOR = "=======================================";
    private final static String ERROR_LOG_NAME = "错误.txt";
    private final static String ERROR_TITLE = "ERROR LOG";
    private final static String ERROR_PREFIX = "ERROR TIME:";

    public static void writeSalaryLog(String logs) {
        writeLog(logs, REPORT_NAME, REPORT_TITLE, REPORT_PREFIX);
    }

    public static void writeErrorLog(String logs) {
        writeLog(logs, ERROR_LOG_NAME, ERROR_TITLE, ERROR_PREFIX);
    }

    private static void writeNewLine(BufferedWriter writer, String log) {
        try {
            writer.write(log + Tools.NEW_LINE);
        } catch (IOException e) {
            System.out.println("Some Thing Wrong Writing Report");
        }
    }

    private static void writeLog(String logs, String logName, String logTitle, String log_prefix) {
        try {
            File reportFile = new File(logName);
            boolean isExisted = reportFile.exists();
            BufferedWriter writer = new BufferedWriter(new FileWriter(logName,true));

            if (!isExisted) {
                writeNewLine(writer, logTitle);
            }

            String[] logInfo = StringUtils.convertLogString(logs);

            writeNewLine(writer, SEPARATOR);
            writeNewLine(writer, log_prefix + Tools.getCurrentTime());
            for (String log : logInfo) {
                writeNewLine(writer, log);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Some Thing Wrong Writing log");
        }
    }
}
