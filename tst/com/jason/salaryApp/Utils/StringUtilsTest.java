package com.jason.salaryApp.Utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StringUtilsTest {

    //Unit tests For Method: isNotBlank
    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(" "));
        Assert.assertTrue(StringUtils.isNotBlank("s"));
    }

    //Unit tests For Method: convertCsvRowString
    @Test
    public void testConvertCsvRowStringForValidRow() {
        String[] result;
        String inputString = ",9:30-5,,,,5-10,,,,,7-11,,7-11,,";
        result = StringUtils.convertCsvRowString(inputString);
        Assert.assertTrue(result[1].equals("9:30-5"));
        Assert.assertEquals(13, result.length);
    }

    @Test
    public void testConvertCsvRowStringForEmptyRow() {
        String[] result;
        String inputString = ",,,,,,,,,,,,,,";
        result = StringUtils.convertCsvRowString(inputString);
        Assert.assertEquals(0, result.length);
    }

    //Unit tests For Method: convertDateString
    @Test
    public void testConvertDateStringForValidString() {
        String[] result;
        String inputString = "2018-01-02";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals("2018", result[0]);
        Assert.assertEquals("01", result[1]);
        Assert.assertEquals("02", result[2]);
        Assert.assertEquals(3, result.length);
    }

    @Test
    public void testConvertDateStringForEmptyString() {
        String[] result;
        String inputString = "";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals(1, result.length);
    }

    //Unit tests For Method: convertWorkHourString
    @Test
    public void testConvertWorkHourString1() {
        String[] result;
        String inputString = "9:30";
        result = StringUtils.convertWorkHourString(inputString);
        Assert.assertEquals("9", result[0]);
        Assert.assertEquals("30", result[1]);
    }

    @Test
    public void testConvertWorkHourString2() {
        String[] result;
        String inputString = "9：30";
        result = StringUtils.convertWorkHourString(inputString);
        Assert.assertEquals("9", result[0]);
        Assert.assertEquals("30", result[1]);
    }

    //Unit tests For Method: convertSalaryString
    @Test
    public void testConvertSalaryStringForValidString() {
        String[] result;
        String inputString = "Jason-8.5";
        result = StringUtils.convertSalaryString(inputString);
        Assert.assertEquals("Jason", result[0]);
        Assert.assertEquals("8.5", result[1]);
    }

    @Test
    public void testConvertSalaryStringForEmptyString() {
        String[] result;
        String inputString = "";
        result = StringUtils.convertSalaryString(inputString);
        Assert.assertEquals(1, result.length);
    }

    //Unit tests For Method: removeBlankPrefix
    @Test
    public void testRemoveBlankPrefix() {
        Assert.assertNull(StringUtils.removeBlankPrefix(" "));
        Assert.assertNull(StringUtils.removeBlankPrefix(null));
        Assert.assertEquals("someString", StringUtils.removeBlankPrefix("   someString"));
    }

    private void printResult(String[] result) {
        System.out.println(Arrays.toString(result));
    }
}
