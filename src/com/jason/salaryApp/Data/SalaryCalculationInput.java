package com.jason.salaryApp.Data;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SalaryCalculationInput {
    private Map<String, List<WorkSlot>> workSlotMap;
    private Map<String, Float> salaryMap;

    public SalaryCalculationInput(Map<String, List<WorkSlot>> workSlotMap, Map<String, Float> salaryMap) {
        this.workSlotMap = workSlotMap;
        this.salaryMap = salaryMap;
    }
}
