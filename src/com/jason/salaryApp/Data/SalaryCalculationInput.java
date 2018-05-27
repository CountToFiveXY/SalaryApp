package com.jason.salaryApp.Data;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class SalaryCalculationInput {
    private HashMap<String, List<WorkSlot>> workSlotMap;
    private HashMap<String, String> salaryMap;
}
