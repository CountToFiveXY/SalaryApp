package com.jason.salaryApp.Reader;

import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class WorkSheetFileReader {

    public static final int COLUMN_NUM = 8;
    private static final String DATE_STRING = "Date";
    private static final String WEEKDAY_STRING = "WeekDay";
    private static final String X = "X";
    private static final List<String> files = Arrays.asList("1.csv", "2.csv", "3.csv");

    //WorkSheet Path
    public static final String WORKSHEET_FILE_PATH = "tables/";
    public static final String TEST_WORKSHEET_FILE_PATH = "resources/testInput/";

    public List<String[]> readWorkSheetFile(String filePath) {
        List<String[]> workSheet = new ArrayList<>();
        try {
            File inputCSV = new File(filePath);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputCSV), StandardCharsets.UTF_8));
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum = lineNum + 1;
                String[] rowContent = StringUtils.convertCsvRowString(line);
                Tools.checkArgument(checkColumnForEachRow(line, rowContent), filePath+ ":line-" + lineNum + ":" + ErrorMessages.ROW_EXCEED_COLUMN + line);
                workSheet.add(modifyEachRow(rowContent));
            }
        } catch (IOException e){
            System.out.println("[Warning]: Missing " + filePath);
        }
        return workSheet;
    }

    public List<String> getFormalFilesPath() {
        return files.stream()
                .map(fileName -> WORKSHEET_FILE_PATH + fileName)
                .collect(Collectors.toList());
    }

    List<String[]> getTestWorkSheet() {
        return readWorkSheetFile(TEST_WORKSHEET_FILE_PATH + "1.csv");
    }

    /*
    1. create another line with 15 X.
    2. if value exists in origin row, remove blank prefix&&suffix and replace X.
    3. If it's Date or WeekDay Row, fill X with Date/WeekDay.
     */
    String[] modifyEachRow(String[] rowContent) {
        String[] modifiedRow = new String[COLUMN_NUM];
        Arrays.fill(modifiedRow, 0, COLUMN_NUM, X);

        for (int i = 0; i < rowContent.length; i++) {
            modifiedRow[i] = StringUtils.isNotBlank(rowContent[i])? modifyRowContent(rowContent[i]) : X;
        }

        modifySpecialRow(modifiedRow);

        return modifiedRow;
    }

    private void modifySpecialRow(String[] modifiedRow) {

        if (isDateRow(modifiedRow)) {
            modifiedRow[0] = DATE_STRING;
            for (int i = 2; i < modifiedRow.length; i+=2) {
                modifiedRow[i] = modifiedRow[i-1];
            }

            for (int i = 1; i < modifiedRow.length; i++) {
                modifiedRow[i] = Tools.formatDate(modifiedRow[i]);
            }
        }

        if (isWeekDayRow(modifiedRow)) {
            modifiedRow[0] = WEEKDAY_STRING;
            for (int i = 2; i < modifiedRow.length; i+=2) {
                modifiedRow[i] = modifiedRow[i-1];
            }
        }
    }

    private String modifyRowContent(String rowContent) {
        String removeBlank = StringUtils.removeBlankPrefixAndSuffix(rowContent);
        return StringUtils.replaceStashString(removeBlank);
    }

    private boolean isDateRow(String[] modifiedRow) {
        return modifiedRow[0].equals(X) && modifiedRow[1].startsWith("2");
    }

    private boolean isWeekDayRow(String[] modifiedRow) {
        return modifiedRow[0].equals(X) && modifiedRow[1].equals("Mon");
    }

    private boolean checkColumnForEachRow(String line, String[] rowContext) {
        int len = (line + "s").split(",").length;
        return rowContext.length == 0 || len == COLUMN_NUM;
    }
}
