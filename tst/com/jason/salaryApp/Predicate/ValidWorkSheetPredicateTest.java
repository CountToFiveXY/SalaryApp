package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Handler.FileHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ValidWorkSheetPredicateTest {
    private ValidWorkSheetPredicate predicate;
    private FileHandler fileHandler;

    @Before
    public void setup() {
        predicate = new ValidWorkSheetPredicate();
        fileHandler = new FileHandler();
    }

    @Test
    public void testNonEmptySlotForGood() {
        List<String[]> input = fileHandler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "1.csv");
        Assert.assertTrue(predicate.testNonEmptySlot(input));
    }

    @Test
    public void testNonEmptySlotForBad() {
        List<String[]> input = fileHandler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "3.csv");
        Assert.assertTrue(predicate.testNonEmptySlot(input));
    }
}
