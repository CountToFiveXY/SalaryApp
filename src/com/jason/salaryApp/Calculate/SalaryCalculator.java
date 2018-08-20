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
            calculateSalaryForOnePerson(input, personName);
        });
    }

    public void calculateSalaryForOnePerson(SalaryCalculationInput input, String personName) {
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

        String personInfoLog = String.format("Salary For %s:", personName);
        AddToLog(personInfoLog);

        List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
        Float salaryPerHour = salaryMap.get(personName);

        workSlotsForThisPerson.forEach(this::logEachWorkSlotAndTotalHour);
        double totalSalary = totalWorkHour * salaryPerHour;
        String sumSalaryLog = String.format("Salary: %.2f($/h) X %.2f(h) = $%.2f",salaryPerHour,totalWorkHour,totalSalary);
        resetTotalWorkHour();
        AddToLog(sumSalaryLog);
    }

    private void logEachWorkSlotAndTotalHour(WorkSlot workSlot) {
        double workTime = workSlot.getWorkTime();
        double preWorkHour = totalWorkHour;
        totalWorkHour += workTime;
        String proceedWorkSlotLog = String.format(
                "%s, Total WorkHour: %.1f + %.1f = %.1f hours."
                , workSlot.toLog(), preWorkHour, workTime, totalWorkHour);
        AddToLog(proceedWorkSlotLog);
    }

    private void AddToLog(String logString) {
        log += logString + Tools.LOG_SEPARATOR;
    }

    private void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
