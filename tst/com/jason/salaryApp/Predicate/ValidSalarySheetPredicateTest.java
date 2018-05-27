package com.jason.salaryApp.Predicate;


import com.jason.salaryApp.Reader.SalaryFileReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.List;

public class ValidSalarySheetPredicateTest {
    private ValidSalarySheetPredicate predicate;
    private SalaryFileReader reader;

    @Before
    public void setup() {
        predicate = new ValidSalarySheetPredicate();
        reader = new SalaryFileReader();
    }

    @Test
    public void testNonEmptySlotForGood() throws NoSuchFileException {
        List<String[]> input = reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + "test_salary_good.txt");
        Assert.assertTrue(predicate.test(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonEmptySlotForBad() throws NoSuchFileException{
        List<String[]> input = reader.readSalaryFile(SalaryFileReader.TEST_SALARY_FILE_PATH + "test_salary_bad.txt");
        Assert.assertFalse(predicate.test(input));
    }
}