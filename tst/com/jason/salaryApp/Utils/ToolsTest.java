package com.jason.salaryApp.Utils;

import org.junit.Test;
import org.junit.Assert;

public class ToolsTest {

    @Test
    public void testIsBetweenTwoDays1() {
        String start = "2011-05-30";
        String end = "2011-6-01";

        Assert.assertTrue(Tools.isBetweenTwoDays("2011-05-30", start, end));
        Assert.assertTrue(Tools.isBetweenTwoDays("2011-06-01", start, end));
        Assert.assertTrue(Tools.isBetweenTwoDays("2011-05-31", start, end));
        Assert.assertFalse(Tools.isBetweenTwoDays("2011-05-15", start, end));
        Assert.assertFalse(Tools.isBetweenTwoDays("2011-06-15", start, end));
    }

    @Test
    public void testCheckArgumentWithRightArgument() {
        String personName = "JingWen";
        Tools.checkArgument(personName.endsWith("n"), "Name was wrong");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgumentWithWrongArgument() {
        String personName = "JingWen";
        Tools.checkArgument(personName.endsWith("J"), "Name was wrong");
    }
}
