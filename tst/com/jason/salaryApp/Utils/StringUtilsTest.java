package com.jason.salaryApp.Utils;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void convertDateStringTest1() {
        String[] result;
        String inputString = "2018-01-02";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals("2018", result[0]);
        Assert.assertEquals("01", result[1]);
        Assert.assertEquals("02", result[2]);
    }

    @Test
    public void convertDateStringTest2() {
        String[] result;
        String inputString = "2018－01－02";
        result = StringUtils.convertDateString(inputString);
        Assert.assertEquals("2018", result[0]);
        Assert.assertEquals("01", result[1]);
        Assert.assertEquals("02", result[2]);
    }

    @Test
    public void convertDateStringTestForEmptyString() {
        String inputString = "";
        StringUtils.convertDateString(inputString);
    }

    @Test
    public void testIsNotBlank() {
        System.out.println(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(" "));
        Assert.assertTrue(StringUtils.isNotBlank("s"));
    }

    @Test
    public void testRemoveBlankPrefix() {
        Assert.assertNull(StringUtils.removeBlankPrefix(" "));
        Assert.assertNull(StringUtils.removeBlankPrefix(null));
        Assert.assertEquals("someString", StringUtils.removeBlankPrefix("   someString"));
    }

    @Test
    public void testConvertWorkSlotString1() {
        String[] result;
        String inputString = "9:30";
        result = StringUtils.convertWorkSlotString(inputString);
        Assert.assertEquals("9", result[0]);
        Assert.assertEquals("30", result[1]);
    }

    @Test
    public void testConvertWorkSlotString2() {
        String[] result;
        String inputString = "9：30";
        result = StringUtils.convertWorkSlotString(inputString);
        Assert.assertEquals("9", result[0]);
        Assert.assertEquals("30", result[1]);
    }
}
