package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Component
public class FileHandler {
    @Getter HashMap<String, Double> salaryMap = new HashMap<>();
    public static final String TEST_FILE_PATH = "resources/testInput/";
    public static final int COLUMN = 15;
    private static final String DATE_STRING = "Date";
    private static final String WEEKDAY_STRING = "Date";
    private static final String X = "X";

    public List<String[]> convertInputCSVFileToArray (String fileName) {
        List<String[]> workSheet = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] rowContent = convertCsvRowContentToArray(line);
                Tools.checkArgument(checkRowValidation(line, rowContent), "[ERROR!] Bad Table row:" + line);
                workSheet.add(modifyEachRow(rowContent));
            }
        } catch (IOException e){
            System.out.println("[Warning]: Missing " + fileName);
        }
        return workSheet;
    }

    String[] modifyEachRow(String[] rowContent) {
        String[] template = new String[COLUMN];
        Arrays.fill(template, 0, COLUMN, X);

        for (int i = 0; i < rowContent.length; i++) {
            template[i] = StringUtils.isNotBlank(rowContent[i])? StringUtils.removeBlankPrefix(rowContent[i]) : X;
        }

        boolean isDateRow = template[0].equals(DATE_STRING) || template[0].equals(WEEKDAY_STRING);

        if (isDateRow) {
            modifyDateRowContent(template);
        }
        return template;
    }

    private boolean checkRowValidation(String line, String[] rowContext) {
        int len = (line + "s").split(",").length;
        return rowContext.length == 0 || len == 15;
    }

    private void modifyDateRowContent(String[] rowContent) {
        for (int i = 2; i < rowContent.length; i+=2) {
            rowContent[i] = rowContent[i-1];
        }
    }

    private String[] convertCsvRowContentToArray(String content) {
        return content.split(",");
    }
}
