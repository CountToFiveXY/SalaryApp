package com.jason.salaryApp.Builder;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Handler.WorkSheetFileReader;
import com.jason.salaryApp.Predicate.ValidWorkSlotPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WorkSlotMapBuilder {

    private ValidWorkSlotPredicate validWorkSlotPredicate = new ValidWorkSlotPredicate();
    private List<String> NonSalaryRowFirstString = Arrays.asList("Date", "WeekDay");

    public HashMap<String, List<WorkSlot>> setUpWorkSlotMapForEachSheet(List<String[]> worksheet) {
        HashMap<String, List<WorkSlot>> personToWorkSlotMap = new HashMap<>();
        String[] dateRow = worksheet.get(0);
        String[] weekDateRow = worksheet.get(1);
        List<String[]> filteredWorkSheet = getWorkSlotTables(worksheet);

        filteredWorkSheet.forEach(rowContent -> {
            String personName = rowContent[0];
            List<WorkSlot> workSlotsForPerson = buildWorkSlotListForPerson(rowContent, dateRow, weekDateRow);
            fillWorkSlotMap(personToWorkSlotMap, personName, workSlotsForPerson);
        });

        return personToWorkSlotMap;
    }

    private List<String[]> getWorkSlotTables(List<String[]> worksheet) {
        return worksheet.stream()
                .filter(rowContent -> !NonSalaryRowFirstString.contains(rowContent[0]))
                .collect(Collectors.toList());
    }

    private List<WorkSlot> buildWorkSlotListForPerson(String[] rowContent, String[] dateRow, String[] weekDateRow) {
        List<WorkSlot> workSlotsForPerson = new ArrayList<>();

        for (int index = 0; index < WorkSheetFileReader.COLUMN; index++) {
            String content = rowContent[index];
            if (validWorkSlotPredicate.test(content)) {
                workSlotsForPerson.add(createWorkSlotByIndex(index, content, dateRow, weekDateRow));
            }
        }

        return workSlotsForPerson;
    }

    private WorkSlot createWorkSlotByIndex(int index, String workSlotString, String[] dateRow, String[] weekDateRow) {
        String date = dateRow[index];
        String workDay = weekDateRow[index];
        return new WorkSlot(workSlotString, date, workDay);
    }

    private void fillWorkSlotMap(HashMap<String, List<WorkSlot>> personToWorkSlotMap, String personName, List<WorkSlot> workSlots) {
        if (!personToWorkSlotMap.containsKey(personName)) {
            personToWorkSlotMap.put(personName, new ArrayList<>());
        }
        personToWorkSlotMap.get(personName).addAll(workSlots);
    }
}
