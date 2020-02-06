package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class SalaryCalculatorTest {

    private static final String personName = "Jason";
    private static final String personName2 = "Jason2";
    private static final String personName3 = "Wendy";
    private static final Float salary = 8.0f;
    private final WorkSlot workSlot1 = new WorkSlot("11am－4pm", "5/10/2018", "Monday");
    private final WorkSlot workSlot2 = new WorkSlot("9:30am－5pm", "5/11/2018", "Tuesday");
    private final List<WorkSlot> workSlots = Arrays.asList(workSlot1, workSlot2);

    private final HashMap<String, List<WorkSlot>> workSlotMap = new HashMap<>();
    private final HashMap<String, Float> salaryMap = new HashMap<>();
    private final Set<String> fullTimeSet = new HashSet<>();

    private SalaryCalculationInput input;
    private SalaryCalculator calculator;

    @Before
    public void setUp() {
        workSlotMap.put(personName, workSlots);
        workSlotMap.put(personName2, workSlots);
        workSlotMap.put(personName3, workSlots);
        salaryMap.put(personName, salary);
        salaryMap.put(personName2, salary);
        fullTimeSet.add(personName3);
        input = new SalaryCalculationInput(workSlotMap, salaryMap, fullTimeSet);
        calculator = new SalaryCalculator();
    }

    @Test
    public void testCalculateForAll() {
        printLog(calculator.calculateForAll(input));
    }

    @Test
    public void testCalculateForOne() {
        printLog(calculator.calculateSalaryForOnePerson(input, personName, true));
    }

    private void printLog(String logString) {
        Tools.print("[INFO]From SalaryCalculator Unit Test: ");
        String[] divideLog = StringUtils.convertLogString(logString);
        Arrays.stream(divideLog).forEach(Tools::print);
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}