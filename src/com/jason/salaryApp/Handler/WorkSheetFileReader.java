package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class WorkSheetFileReader {
    public static final String WORKSHEET_FILE_PATH = "tables/";
    public static final String TEST_WORKSHEET_FILE_PATH = "resources/testInput/";
    public static final int COLUMN = 15;
    private static final String DATE_STRING = "Date";
    private static final String WEEKDAY_STRING = "WeekDay";
    private static final String X = "X";

    public List<String[]> readWorkSheetFile(String fileName) {
        List<String[]> workSheet = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowContent = StringUtils.convertCsvRowString(line);
                Tools.checkArgument(checkRowValidation(line, rowContent), "[ERROR!] Bad Table row:" + line);
                workSheet.add(modifyEachRow(rowContent));
            }
        } catch (IOException e){
            System.out.println("[Warning]: Missing " + fileName);
        }
        return workSheet;
    }

    public List<String[]> generateTestWorkSheet() {
        return readWorkSheetFile(TEST_WORKSHEET_FILE_PATH + "1.csv");
    }

    /*
    1. Fill each blank slot with X
    2. remove blank prefix for each String
    3. If is Date or WeekDay Row, Modify it with value filled
     */
    String[] modifyEachRow(String[] rowContent) {
        String[] modifiedRow = new String[COLUMN];
        Arrays.fill(modifiedRow, 0, COLUMN, X);

        for (int i = 0; i < rowContent.length; i++) {
            modifiedRow[i] = StringUtils.isNotBlank(rowContent[i])? StringUtils.removeBlankPrefix(rowContent[i]) : X;
        }

        boolean isDateOrWeekDayRow = modifiedRow[0].equals(DATE_STRING) || modifiedRow[0].equals(WEEKDAY_STRING);

        if (isDateOrWeekDayRow) {
            modifySpecialRowContent(modifiedRow);
        }
        return modifiedRow;
    }

    private void modifySpecialRowContent(String[] rowContent) {
        for (int i = 2; i < rowContent.length; i+=2) {
            rowContent[i] = rowContent[i-1];
        }
    }

    private boolean checkRowValidation(String line, String[] rowContext) {
        int len = (line + "s").split(",").length;
        return rowContext.length == 0 || len == 15;
    }
}
