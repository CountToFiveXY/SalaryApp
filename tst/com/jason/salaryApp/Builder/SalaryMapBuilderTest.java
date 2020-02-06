package com.jason.salaryApp.Builder;

import com.jason.salaryApp.Reader.SalaryFileReader;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SalaryMapBuilderTest {
    private SalaryFileReader reader = new SalaryFileReader();
    private SalaryMapBuilder builder = new SalaryMapBuilder();

    @Test
    public void testSetUpWorkSlotMapForEachSheet() throws NoSuchFileException {
        List<String[]> input = getInputTable("test_salary_good.txt");
        HashMap<String, Float> result = builder.buildSalaryMap(input);
        printMap(result);
    }

    @Test
    public void testSetUpFullTimeSetForEachSheet() throws NoSuchFileException {
        List<String[]> input = getInputTable("test_salary_good.txt");
        Set<String> result = builder.buildFullTimeSet(input);
        Assert.assertTrue(result.contains("JINGWEN"));
    }

    private List<String[]> getInputTable(String fileName) throws NoSuchFileException{
        return reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + fileName);
    }

    private void printMap(HashMap<String, Float> map) {
        Tools.print("[INFO]From SalaryMapBuilderTest Unit Test: ");
        map.forEach((personName, salary) -> {
            Tools.print(personName + "->" + salary.toString());
        });
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}