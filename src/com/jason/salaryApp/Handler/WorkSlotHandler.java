package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkSlotHandler {
    @Getter static HashMap<String, WorkSlot> personToWorkSlotMap = new HashMap<>();

    private List<String> NonSalaryRowFirstString = Arrays.asList("X", "Date", "WeekDay");

    public void createWorkSlotsForWorkSheet(List<String[]> worksheet) {
        //TODO: Add a check here For worksheet
        String[] dateRow = worksheet.get(0);
        String[] weekDateRow = worksheet.get(1);
        List<String[]> filteredWorkSheet = getWorkSlotTables(worksheet);

    }


    private List<String[]> getWorkSlotTables(List<String[]> worksheet) {
        return worksheet.stream()
                .filter(rowContent -> !NonSalaryRowFirstString.contains(rowContent[0]))
                .collect(Collectors.toList());
    }
}
