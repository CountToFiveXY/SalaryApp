package com.jason.salaryApp.Predicate;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class ValidDatePredicateTest {
    private ValidDatePredicate predicate;

    @Before
    public void setup() {
        predicate = new ValidDatePredicate();
    }

    @Test
    public void testValidDateString() {
        String date = "2020-01-07";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testValidDateString1() {
        String date = "2020-01-31";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testValidDateString2() {
        String date = "2020-12-04";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testEmptyString() {
        String date = "";
        boolean result = predicate.test(date);
        assertFalse(result);
    }
}