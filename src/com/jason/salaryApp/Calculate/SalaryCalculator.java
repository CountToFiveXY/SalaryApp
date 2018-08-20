package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;

import java.util.List;
import java.util.Map;

public class SalaryCalculator {

    public String log = "";
    private double totalWorkHour = 0.0;

    public void calculateForAll(SalaryCalculationInput input) {
        input.getWorkSlotMap().keySet().forEach(personName -> {
            calculateSalaryForOnePerson(input, personName, false);
        });
    }

    public void calculateSalaryForOnePerson(SalaryCalculationInput input, String personName, boolean isSingleSearch) {
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

        String personInfoLog = String.format("%s该时段总工资为:", personName);
        AddToLog(personInfoLog);

        List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
        Float salaryPerHour = salaryMap.get(personName);

        workSlotsForThisPerson.forEach(workSlot -> logEachWorkSlotAndTotalHour(workSlot, isSingleSearch));

        double totalSalary = totalWorkHour * salaryPerHour;
        String sumSalaryLog = String.format("%.2f(h) X %.2f($/h) = $%.2f",totalWorkHour, salaryPerHour, totalSalary);
        resetTotalWorkHour();
        AddToLog(sumSalaryLog);
    }

    private void logEachWorkSlotAndTotalHour(WorkSlot workSlot, boolean isSingleSearch) {
        double workTime = workSlot.getWorkTime();
        double preWorkHour = totalWorkHour;
        totalWorkHour += workTime;
        String proceedWorkSlotLog = String.format(
                "%s, Total WorkHour: %.1f + %.1f = %.1f hours."
                , workSlot.toLog(), preWorkHour, workTime, totalWorkHour);

        //apply log only for single Search
        if (isSingleSearch) {
            AddToLog(proceedWorkSlotLog);
        }
    }

    private void AddToLog(String logString) {
        log += logString + Tools.LOG_SEPARATOR;
    }

    private void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
