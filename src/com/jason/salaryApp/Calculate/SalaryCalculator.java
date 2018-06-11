package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;

import java.util.List;
import java.util.Map;

public class SalaryCalculator {

    public void calculate(SalaryCalculationInput input) {
        Map<String, List<WorkSlot>> workSlotsMap = input.getWorkSlotMap();
        Map<String, Float> salaryMap = input.getSalaryMap();

    }
}
