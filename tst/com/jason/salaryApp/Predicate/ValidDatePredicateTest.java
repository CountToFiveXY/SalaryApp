package com.jason.salaryApp.Predicate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidDatePredicateTest {
    private ValidDatePredicate predicate;

    @Before
    public void setup() {
        predicate = new ValidDatePredicate();
    }

    @Test
    public void testValidDateString() {
        String date = "2018-01-07";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testUnValidDateString1() {
        String date = "2018－01－07";
        boolean result = predicate.test(date);
        assertFalse(result);
    }

    @Test
    public void testUnValidDateString2() {
        String date = "2018-1-07";
        boolean result = predicate.test(date);
        assertFalse(result);
    }

    @Test
    public void testEmptyString() {
        String date = "";
        boolean result = predicate.test(date);
        assertFalse(result);
    }
}