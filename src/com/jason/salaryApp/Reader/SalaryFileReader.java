package com.jason.salaryApp.Reader;

import com.jason.salaryApp.Utils.StringUtils;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

public class SalaryFileReader {
    public static final String SALARY_FILE_PATH = "salary.txt";
    public static final String TEST_SALARY_FILE_PATH = "resources/testInput/";

    public List<String[]> readSalaryFile (String fileName) throws NoSuchFileException{
        List<String[]> salaryContent = new ArrayList<>();
        try {
            File inputCSV = new File(fileName);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(inputCSV), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                salaryContent.add(StringUtils.convertSalaryString(line));
            }
        } catch (IOException e){
            throw new NoSuchFileException("[ERROR] Salary File Missing");
        }
        return salaryContent;
    }

    public List<String[]> getFormalSalaryFile() throws NoSuchFileException{
        return readSalaryFile(SALARY_FILE_PATH);
    }
}
