package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalaryCalculator {

    public String log = "";
    private double totalWorkHour = 0.0;

    public void calculate(SalaryCalculationInput input) {
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

        workSlotsMap.keySet().forEach(personName -> {
            String personInfoLog = String.format("Salary Info For %s:%s", personName, Tools.LOG_SEPARATOR);
            AddToLog(personInfoLog);
            List<WorkSlot> workSlotsForThisPerson = workSlotsMap.get(personName);
            Float salaryPerHour = salaryMap.get(personName);
            calculateWholeSalaryForThisPerson(workSlotsForThisPerson, salaryPerHour);
        });
    }

    private double calculateWholeSalaryForThisPerson(List<WorkSlot> workSlotsForThisPerson, Float salaryPerHour) {
        workSlotsForThisPerson.forEach(workSlot -> logEachWorkSlot(workSlot));
        Double totalSalary = totalWorkHour * salaryPerHour;
        String sumSalaryLog = String.format("%.2f($/h) X %.2f(h) = $%.2f%s",salaryPerHour,totalWorkHour,totalSalary, Tools.LOG_SEPARATOR);
        resetTotalWorkHour();
        AddToLog(sumSalaryLog);
        return totalSalary;
    }

    private void logEachWorkSlot(WorkSlot workSlot) {
        double workTime = workSlot.getWorkTime();
        double preWorkHour = totalWorkHour;
        totalWorkHour += workTime;
        String proceedWorkSlotLog = String.format("%s, Total WorkHour: %.1f + %.1f = %.1f hours.%s", workSlot.toLog(), preWorkHour, workTime, totalWorkHour, Tools.LOG_SEPARATOR);
        AddToLog(proceedWorkSlotLog);
    }

    private void AddToLog(String logString) {
        log += logString;
    }

    private void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
