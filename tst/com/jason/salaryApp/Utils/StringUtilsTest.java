package com.jason.salaryApp.Utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    //Unit tests For Method: isNotBlank
    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(" "));
        Assert.assertTrue(StringUtils.isNotBlank("s"));
    }

    //Unit tests For Method: isFloat
    @Test
    public void testIsFloat() {
        Assert.assertFalse(StringUtils.isFloat(null));
        Assert.assertFalse(StringUtils.isFloat(""));
        Assert.assertFalse(StringUtils.isFloat(" "));
        Assert.assertFalse(StringUtils.isFloat("s"));
        Assert.assertTrue(StringUtils.isFloat("1"));
        Assert.assertTrue(StringUtils.isFloat("2.5"));
    }

    //Unit tests For Method: convertCsvRowString
    @Test
    public void testConvertCsvRowStringForValidRow() {
        String[] result;
        String inputString = ",9:30-5,,,,5-10,,,,,7-11,,7-11,,";
        result = StringUtils.convertCsvRowString(inputString);
        Assert.assertEquals("9:30-5", result[1]);
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
        String inputString = "2018-1-2";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals("1", result[1]);
        Assert.assertEquals("2", result[2]);
        Assert.assertEquals("2018", result[0]);
        Assert.assertEquals(3, result.length);
    }

    @Test
    public void testConvertDateStringForEmptyString() {
        String[] result;
        String inputString = "";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals(1, result.length);
    }

    //Unit tests For Method: convertWorkSlotString
    @Test
    public void testConvertWorkSlotString() {
        String[] result;
        String inputString = "11am-10pm";
        result = StringUtils.convertWorkSlotString(inputString);
        Assert.assertEquals("11am", result[0]);
        Assert.assertEquals("10pm", result[1]);
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

    @Test
    public void testConvertWorkHourString3() {
        String[] result;
        String inputString = "5";
        result = StringUtils.convertWorkHourString(inputString);
        Assert.assertEquals("5", result[0]);
        Assert.assertEquals(1,result.length);
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

    //Unit tests for Method: convertLogString
    @Test
    public void testConvertLogString() {
        String[] result;
        String inputString = "I love Wendy@Wendy loves me";
        result = StringUtils.convertLogString(inputString);
        Assert.assertTrue(result.length == 2);
    }

    //Unit tests for Method: convertTimeToNumberFormat
    @Test
    public void testConvertTimeToNumberFormat1() {
        float result;
        String inputString = "9:30am";
        result = StringUtils.convertTimeToNumberFormat(inputString);
        Assert.assertTrue(result == 9.5);
    }

    @Test
    public void testConvertTimeToNumberFormat2() {
        float result;
        String inputString = "5:30pm";
        result = StringUtils.convertTimeToNumberFormat(inputString);
        Assert.assertTrue(result == 17.5);
    }

    //Unit tests For Method: removeBlankPrefixAndSuffix
    @Test
    public void testRemoveBlankPrefix() {
        Assert.assertNull(StringUtils.removeBlankPrefixAndSuffix(" "));
        Assert.assertNull(StringUtils.removeBlankPrefixAndSuffix(null));
        Assert.assertEquals("someString", StringUtils.removeBlankPrefixAndSuffix("   someString"));
        Assert.assertEquals("someString", StringUtils.removeBlankPrefixAndSuffix("   someString     "));
    }

    @Test
    public void testReplaceStash() {
        String inputString = "9:30－4";
        Assert.assertEquals("9:30-4", StringUtils.replaceStashString(inputString));
    }
}
