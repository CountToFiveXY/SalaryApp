package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ValidSalaryCalculationInputPredicateTest {

    private HashMap<String, List<WorkSlot>> workSlotsMap;
    private HashMap<String, Float> salaryMap;
    private Set<String> fullTimeSet;
    private SalaryCalculationInput  input;
    private ValidSalaryCalculationInputPredicate predicate;

    @Before
    public void setUp() {
        buildMap();
        input = new SalaryCalculationInput(workSlotsMap, salaryMap, fullTimeSet);
        predicate = new ValidSalaryCalculationInputPredicate();
    }

    @Test
    public void testForGoodInput() {
        AddWorkSlotMap("Jason");
        boolean result = predicate.test(input);
        assertTrue(result);
    }

    @Test
    public void testForGoodInput2() {
        AddWorkSlotMap("Wendy");
        boolean result = predicate.test(input);
        assertTrue(result);
    }

    @Test
    public void testForBadInput() {
        AddWorkSlotMap("Tina");
        boolean result = predicate.test(input);
        assertFalse(result);
    }

    @Test
    public void testGetUnSalariedPeopleNameForBadInput() {
        AddWorkSlotMap("Tina");
        List<String> unSalaried = predicate.getUnSalariedPeopleName(input);
        assertTrue(unSalaried.contains("Tina"));
    }

    private void AddWorkSlotMap(String personName) {
        workSlotsMap.put(personName, new ArrayList<>());
    }

    private void buildMap() {
        workSlotsMap = new HashMap<>();
        salaryMap = new HashMap<>();
        fullTimeSet = new HashSet<>();
        salaryMap.put("Jason", 8.5f);
        salaryMap.put("*Wendy", 10.0f);
        fullTimeSet.add("Wendy");
    }
}