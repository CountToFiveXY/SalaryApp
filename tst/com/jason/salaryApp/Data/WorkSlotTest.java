package com.jason.salaryApp.Data;

import org.junit.Assert;
import org.junit.Test;


public class WorkSlotTest {

    WorkSlot testWorkSlot1 = new WorkSlot("11－4", "2018-05-10", "Mon");
    WorkSlot testWorkSlot2 = new WorkSlot("9:30－5", "2018-05-10", "Mon");

    @Test
    public void testGetWorkTime1() {
        double result;
        result = testWorkSlot1.getWorkTime();
        Assert.assertTrue(result == 5.0);
    }

    @Test
    public void testGetWorkTime2() {
        double result;
        result = testWorkSlot2.getWorkTime();
        Assert.assertTrue(result == 7.5);
    }
}