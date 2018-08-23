package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Builder.SalaryEasterEggBuilder;
import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;

import java.util.List;
import java.util.Map;

public class SalaryCalculator {

    private double totalWorkHour = 0.0;

    public String calculateForAll(SalaryCalculationInput input) {
        StringBuilder logForAll = new StringBuilder();
        for (String personName : input.getWorkSlotMap().keySet()) {
            logForAll.append(calculateSalaryForOnePerson(input, personName, false));
        }
        return logForAll.toString();
    }

    public String calculateSalaryForOnePerson(SalaryCalculationInput input, String personName, boolean isSingleSearch) {
        StringBuilder logForOne = new StringBuilder();
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

        String personInfoLog = String.format("%s该时段总工资为:", personName);
        AddToLog(personInfoLog, logForOne);

        List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
        Float salaryPerHour = salaryMap.get(personName);

        workSlotsForThisPerson.forEach(workSlot -> logEachWorkSlotAndTotalHour(workSlot, isSingleSearch, logForOne));

        double totalSalary = totalWorkHour * salaryPerHour;
        String sumSalaryLog = String.format("%.2f(h) X %.2f($/h) = $%.2f%s",totalWorkHour, salaryPerHour, totalSalary, SalaryEasterEggBuilder.easterEgg(totalSalary));
        resetTotalWorkHour();
        AddToLog(sumSalaryLog, logForOne);
        return logForOne.toString();
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
            AddToLog(proceedWorkSlotLog, logForOne);
        }
    }

    private void AddToLog(String logString, StringBuilder sb) {
        sb.append(logString + Tools.LOG_SEPARATOR);
    }

    private void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
