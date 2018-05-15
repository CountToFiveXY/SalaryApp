package com.jason.salaryApp.Handler;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FileHandlerTest {
    private final FileHandler handler = new FileHandler();


    @Test
    public void testConvertInputCSVFileToArrayForTestInput1() {
        List<String[]> result = handler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "1.csv");
        Assert.assertEquals("Date", result.get(0)[0]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForTestInput2() {
        List<String[]> result = handler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "2.csv");
        Assert.assertEquals("WeekDay", result.get(1)[0]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForTestInput3() {
        List<String[]> result = handler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "3.csv");
        Assert.assertEquals("2018-4-30", result.get(0)[1]);
        printWorkSheet(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertInputCSVFileToArrayForTestInputBad() {
        List<String[]> result = handler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "bad.csv");
        Assert.assertEquals("2018-4-23", result.get(0)[1]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForNonExistedFile() {
        handler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "4.csv");
    }

    @Test
    public void testModifyEachRow1() {
        String[] input = new String[]{"1", "", " 2"};
        String[] result = handler.modifyEachRow(input);
        Assert.assertEquals(15, result.length);
        Assert.assertEquals("1", result[0]);
        Assert.assertEquals("X", result[1]);
        Assert.assertEquals("2", result[2]);
    }

    @Test
    public void testModifyEachRow2() {
        String[] input = new String[]{"Cindy", "", " ", " 5-10"};
        String[] result = handler.modifyEachRow(input);
        Assert.assertEquals(15, result.length);
        Assert.assertEquals("Cindy", result[0]);
        Assert.assertEquals("X", result[1]);
        Assert.assertEquals("5-10", result[3]);
        Assert.assertEquals("X", result[13]);
    }

    private void printWorkSheet(List<String[]> workSheet) {
        workSheet.forEach(row -> System.out.println(Arrays.toString(row)));
    }
}
