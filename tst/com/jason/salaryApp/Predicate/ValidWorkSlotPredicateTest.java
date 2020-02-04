package com.jason.salaryApp.Predicate;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidWorkSlotPredicateTest {
    private ValidWorkSlotPredicate predicate;

    @Before
    public void setup() {
        predicate = new ValidWorkSlotPredicate();
    }

    @Test
    public void testValidWorkSlotString1() {
        String date = "11am-10pm";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testValidWorkSlotString2() {
        String date = "5pm-9:30pm";
        boolean result = predicate.test(date);
        assertTrue(result);
    }

    @Test
    public void testUnValidDateString() {
        String date = "";
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