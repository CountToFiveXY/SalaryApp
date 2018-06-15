package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class SalaryCalculatorTest {

    private static final String personName = "Jason";
    private static final String personName2 = "Jason2";
    private static final Float salary = 8.0f;
    private final WorkSlot workSlot1 = new WorkSlot("11－4", "2018-05-10", "Mon");
    private final WorkSlot workSlot2 = new WorkSlot("9:30－5", "2018-05-11", "Tue");
    private final List<WorkSlot> workSlots = Arrays.asList(workSlot1, workSlot2);

    private final HashMap<String, List<WorkSlot>> workSlotMap = new HashMap<>();
    private final HashMap<String, Float> salaryMap = new HashMap<>();

    private SalaryCalculationInput input;
    private SalaryCalculator calculator;

    @Before
    public void setUp() {
        workSlotMap.put(personName, workSlots);
        workSlotMap.put(personName2, workSlots);
        salaryMap.put(personName, salary);
        salaryMap.put(personName2, salary);
        input = new SalaryCalculationInput(workSlotMap, salaryMap);
        calculator = new SalaryCalculator();
    }

    @Test
    public void testCalculate() {
        calculator.calculate(input);
        System.out.println(calculator.log);
    }
}