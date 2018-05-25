package com.jason.salaryApp.Data;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class SalaryCalculationInput {
    private final HashMap<String, List<WorkSlot>> workSlotMap;
    private final HashMap<String, String> salaryMap;
}
