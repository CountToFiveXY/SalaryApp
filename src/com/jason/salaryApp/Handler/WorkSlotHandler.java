package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Predicate.ValidWorkSlotPredicate;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkSlotHandler {
    @Getter
    static HashMap<String, List<WorkSlot>> personToWorkSlotMap = new HashMap<>();
    @Autowired
    private ValidWorkSlotPredicate validWorkSlotPredicate;

    private List<String> NonSalaryRowFirstString = Arrays.asList("X", "Date", "WeekDay");


    public void setUpWorkSlotMapForEachSheet(List<String[]> worksheet) {
        //TODO: Add a check here For worksheet
        String[] dateRow = worksheet.get(0);
        String[] weekDateRow = worksheet.get(1);
        List<String[]> filteredWorkSheet = getWorkSlotTables(worksheet);

        filteredWorkSheet.forEach(rowContent -> {
            String personName = rowContent[0];
            List<WorkSlot> workSlots = Arrays.stream(rowContent)
                    .filter(validWorkSlotPredicate)
                    .map(workSlot -> createWorkSlot(workSlot, rowContent, dateRow, weekDateRow))
                    .collect(Collectors.toList());
            fillMap(personName, workSlots);
        });
    }

    private void fillMap(String personName, List<WorkSlot> workSlots) {
        if (!personToWorkSlotMap.containsKey(personName)) {
            personToWorkSlotMap.put(personName, new ArrayList<>());
        }
        personToWorkSlotMap.get(personName).addAll(workSlots);
    }


    private List<String[]> getWorkSlotTables(List<String[]> worksheet) {
        return worksheet.stream()
                .filter(rowContent -> !NonSalaryRowFirstString.contains(rowContent[0]))
                .collect(Collectors.toList());
    }

    private WorkSlot createWorkSlot(String workSlotString, String[] rowContent, String[] dateRow, String[] weekDateRow) {
        int index = Arrays.asList(rowContent).indexOf(workSlotString);
        String date = dateRow[index];
        String workDay = weekDateRow[index];
        return new WorkSlot(workSlotString, date, workDay);
    }
}
