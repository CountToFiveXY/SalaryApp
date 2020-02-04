package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TipCalculator extends Calculator{

    private final double totalTip;
    private static final String EMPLOYEE_TIP_LOG = "[员工] %s该时段实得小费为:";
    private List<String> kitchenPeople = Arrays.asList("Jing Bing", "Jing wen");

    public TipCalculator(double tipNumber) {
        totalTip = tipNumber;
    }

    public String calculateTipsForAll(SalaryCalculationInput input) {
        StringBuilder logBuilderForAll = new StringBuilder();

        List<String> personNames = input.getWorkSlotMap().keySet()
                .stream()
                .filter(p -> !kitchenPeople.contains(p))
                .collect(Collectors.toList());

        sortPersonNameList(personNames);

        double totalWorkHour = 0.0;

        for (String personName : personNames) {
            totalWorkHour += getWorkHourForPerson(input, personName);
        }

        for (String personName : personNames) {
            String tipLogForOnePerson = calculateTipForOnePerson(input, personName, totalWorkHour);
            logBuilderForAll.append(tipLogForOnePerson);
        }

        return logBuilderForAll.toString();
    }

    public String calculateTipForOnePerson(SalaryCalculationInput input, String personName, double totalWorkHour) {
        StringBuilder logBuilderForOne = new StringBuilder();
        String personInfoLog = String.format(EMPLOYEE_TIP_LOG, personName);

        AddToLogBuilder(personInfoLog, logBuilderForOne);

        double workHourForPerson = getWorkHourForPerson(input, personName);

        double tipForOnePerson = totalTip * workHourForPerson / totalWorkHour;

        String tipSumLog = String.format("%.2f(h) / %.2f(h) X %.2f($) = $%.2f", workHourForPerson, totalWorkHour, totalTip, tipForOnePerson);
        AddToLogBuilder(tipSumLog, logBuilderForOne);

        resetTotalWorkHour();
        return logBuilderForOne.toString();
    }

    private double getWorkHourForPerson (SalaryCalculationInput input, String personName) {
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
        double totalWorkHourForOnePerson = 0.0;
        for (WorkSlot workSlot : workSlotsForThisPerson) {
            totalWorkHourForOnePerson += workSlot.getWorkTime();
        }
        return totalWorkHourForOnePerson;
    }

    private void sortPersonNameList(List<String> personNames) {
        personNames.sort(Comparator.comparing(kitchenPeople::contains));
    }
}
