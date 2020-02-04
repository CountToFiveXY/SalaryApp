package com.jason.salaryApp.Data;

import com.jason.salaryApp.Utils.Tools;
import org.junit.Assert;
import org.junit.Test;

public class WorkSlotTest {

    WorkSlot testWorkSlot1 = new WorkSlot("11am－4pm", "1/29/2020", "Monday");
    WorkSlot testWorkSlot2 = new WorkSlot("9:30am－5pm", "1/29/2020", "Tuesday");

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

    @Test
    public void testToLog() {
        printWorkSlot(testWorkSlot1);
    }

    private void printWorkSlot(WorkSlot workSlot) {
        Tools.print("[INFO]From WorkSlot Unit Test: ");
        Tools.print(workSlot.toLog());
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}