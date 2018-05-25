package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Utils.Tools;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class WorkSheetFileReaderTest {
    private final WorkSheetFileReader reader = new WorkSheetFileReader();

    @Test
    public void testConvertInputCSVFileToArrayForTestInput1() {
        List<String[]> result = reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "1.csv");
        Assert.assertEquals("Date", result.get(0)[0]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForTestInput2() {
        List<String[]> result = reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "2.csv");
        Assert.assertEquals("WeekDay", result.get(1)[0]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForTestInput3() {
        List<String[]> result = reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "3.csv");
        Assert.assertEquals("2018-4-30", result.get(0)[1]);
        printWorkSheet(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertInputCSVFileToArrayForTestInputBad() {
        List<String[]> result = reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "bad.csv");
        Assert.assertEquals("2018-4-23", result.get(0)[1]);
        printWorkSheet(result);
    }

    @Test
    public void testConvertInputCSVFileToArrayForNonExistedFile() {
        reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "4.csv");
    }

    @Test
    public void testModifyEachRow1() {
        String[] input = new String[]{"1", "", " 2"};
        String[] result = reader.modifyEachRow(input);
        Assert.assertEquals(15, result.length);
        Assert.assertEquals("1", result[0]);
        Assert.assertEquals("X", result[1]);
        Assert.assertEquals("2", result[2]);
    }

    @Test
    public void testModifyEachRow2() {
        String[] input = new String[]{"Cindy", "", " ", " 5-10"};
        String[] result = reader.modifyEachRow(input);
        Assert.assertEquals(15, result.length);
        Assert.assertEquals("Cindy", result[0]);
        Assert.assertEquals("X", result[1]);
        Assert.assertEquals("5-10", result[3]);
        Assert.assertEquals("X", result[13]);
    }

    @Test
    public void testGeneratingTestWorkSheet() {
        printWorkSheet(reader.generateTestWorkSheet());
    }

    private void printWorkSheet(List<String[]> workSheet) {
        Tools.print("[INFO]From WorkSheetFileReader Unit Test: ");
        workSheet.forEach(row -> System.out.println(Arrays.toString(row)));
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}
