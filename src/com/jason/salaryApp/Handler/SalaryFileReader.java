package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Utils.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalaryFileReader {
    public static final String SALARY_FILE_PATH = "test_salary_good.txt";
    public static final String TEST_SALARY_FILE_PATH = "resources/testInput/";

    public List<String[]> readSalaryFile (String fileName) {
        List<String[]> salaryContent = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                salaryContent.add(StringUtils.convertSalaryString(line));
            }
        } catch (IOException e){
            System.out.println("[ERROR]: Missing Salary File" + fileName);
        }
        return salaryContent;
    }
}