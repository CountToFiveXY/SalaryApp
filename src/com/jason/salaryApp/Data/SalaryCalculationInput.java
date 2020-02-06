package com.jason.salaryApp.Data;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class SalaryCalculationInput {
    private Map<String, List<WorkSlot>> workSlotMap;
    private Map<String, Float> salaryMap;
    private Set<String> fullTimeSet;

    public SalaryCalculationInput(Map<String, List<WorkSlot>> workSlotMap, Map<String, Float> salaryMap, Set<String> fullTimeSet) {
        this.workSlotMap = workSlotMap;
        this.salaryMap = salaryMap;
        this.fullTimeSet = fullTimeSet;
    }
}
