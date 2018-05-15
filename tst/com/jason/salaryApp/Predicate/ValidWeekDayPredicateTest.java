package com.jason.salaryApp.Predicate;

import org.junit.Assert;
import org.junit.Test;

public class ValidWeekDayPredicateTest {
    private ValidWeekDayPredicate predicate = new ValidWeekDayPredicate();

    @Test
    public void testPredicate1() {
        Assert.assertTrue(predicate.test("Mon"));
    }

    @Test
    public void testPredicate2() {
        Assert.assertFalse(predicate.test("WeekDay"));
    }
}
