package com.jason.salaryApp.Handler;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SalaryFileReaderTest {
    private SalaryFileReader reader = new SalaryFileReader();

    @Test
    public void testReadSalaryFileForValidFile() {
        List<String[]> result = reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + "test_salary_good.txt");
        Assert.assertEquals("Jason", result.get(0)[0]);
        Assert.assertEquals("10.0", result.get(1)[1]);
        printSalarySheet(result);
    }

    @Test
    public void testReadSalaryFileForInValidFile() {
        List<String[]> result = reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + "test_salary_bad.txt");
        Assert.assertEquals("Jason", result.get(0)[0]);
        printSalarySheet(result);
    }

    @Test
    public void testReadSalaryFileForNonExistedFile() {
        List<String[]> result = reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + "test_salary_ghost.txt");
    }

    private void printSalarySheet(List<String[]> workSheet) {
        System.out.println("\n[INFO]From SalaryFileReader Unit Test: ");
        workSheet.forEach(row -> System.out.println(Arrays.toString(row)));
    }
}