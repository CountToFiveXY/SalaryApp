package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Reader.WorkSheetFileReader;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class ValidWorkTablePredicateTest {
    private ValidWorkTablePredicate predicate;
    private WorkSheetFileReader workSheetFileReader;

    @Before
    public void setup() {
        predicate = new ValidWorkTablePredicate();
        workSheetFileReader = new WorkSheetFileReader();
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testNonEmptySlotForGood() {
        List<String[]> input = workSheetFileReader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "1.csv");
        assertTrue(predicate.test(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonEmptySlotForBad() {
        List<String[]> input = workSheetFileReader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + "3.csv");
        predicate.test(input);
    }

    private void printWorkSheet(List<String[]> workSheet) {
        workSheet.forEach(row -> System.out.println(Arrays.toString(row)));
    }
}