package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Builder.SalaryEasterEggBuilder;
import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SalaryCalculator {

    private double totalWorkHour = 0.0;
    private static final String FULL_TIME_LOG = "[全职] 全职无需结算工资: ";
    private static final String EMPLOYEE_LOG = "[员工] %s该时段总工资为:";
    private List<String> fullTimePeople = Arrays.asList("JING WEN", "Jing Shui", "Jing Bing");

    public String calculateForAll(SalaryCalculationInput input) {
        StringBuilder logForAll = new StringBuilder();
        for (String personName : input.getWorkSlotMap().keySet()) {
            logForAll.append(calculateSalaryForOnePerson(input, personName, false));
        }
        return logForAll.toString();
    }

    public String calculateSalaryForOnePerson(SalaryCalculationInput input, String personName, boolean isSingleSearch) {
        StringBuilder logForOneBuilder = new StringBuilder();
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

        if (fullTimePeople.contains(personName)) {
            AddToLogBuilder(FULL_TIME_LOG + personName, logForOneBuilder);
            return logForOneBuilder.toString();
        }

        String personInfoLog = String.format(EMPLOYEE_LOG, personName);
        AddToLogBuilder(personInfoLog, logForOneBuilder);

        List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
        Float salaryPerHour = salaryMap.get(personName);

        workSlotsForThisPerson.forEach(workSlot -> logEachWorkSlotAndTotalHour(workSlot, isSingleSearch, logForOneBuilder));

        double totalSalary = totalWorkHour * salaryPerHour;
        String sumSalaryLog = String.format("%.2f(h) X %.2f($/h) = $%.2f%s",totalWorkHour, salaryPerHour, totalSalary, SalaryEasterEggBuilder.easterEgg(totalSalary));
        resetTotalWorkHour();
        AddToLogBuilder(sumSalaryLog, logForOneBuilder);
        return logForOneBuilder.toString();
    }

    private void logEachWorkSlotAndTotalHour(WorkSlot workSlot, boolean isSingleSearch, StringBuilder logForOne) {
        double workTime = workSlot.getWorkTime();
        double preWorkHour = totalWorkHour;
        totalWorkHour += workTime;
        String proceedWorkSlotLog = String.format(
                "%s, Total WorkHour: %.1f + %.1f = %.1f hours."
                , workSlot.toLog(), preWorkHour, workTime, totalWorkHour);

        //apply log only for single Search
        if (isSingleSearch) {
            AddToLogBuilder(proceedWorkSlotLog, logForOne);
        }
    }

    private void AddToLogBuilder(String logString, StringBuilder sb) {
        sb.append(logString + Tools.LOG_SEPARATOR);
    }

    private void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
