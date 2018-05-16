package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Handler.WorkSheetFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidWorkSheetPredicateTest {
    private WorkSheetPredicate predicate;
    private WorkSheetFileReader workSheetFileReader;

    @Before
    public void setup() {
        predicate = new WorkSheetPredicate();
        workSheetFileReader = new WorkSheetFileReader();
    }

    @Test
    public void testNonEmptySlotForGood() {
        List<String[]> input = workSheetFileReader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "1.csv");
        Assert.assertTrue(predicate.testNonEmptySlot(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonEmptySlotForBad() {
        List<String[]> input = workSheetFileReader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "3.csv");
        Assert.assertFalse(predicate.testNonEmptySlot(input));
    }
}
