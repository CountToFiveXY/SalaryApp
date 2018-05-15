package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Handler.FileHandler;
import com.jason.salaryApp.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    @Test
    public void testWorkSheetColumns1() {

    }

    @Test
    public void testWorkSheetColumns2() {
        List<String[]> input = fileHandler.convertInputCSVFileToArray(FileHandler.TEST_FILE_PATH + "3.csv");
        Assert.assertTrue(predicate.testWorkSheetColumns(input));
    }

    private List<String[]> createBadInput() {
        List<String[]> input =  new ArrayList<>();
        input.add(new String[] {});
        return input;
    }
}
