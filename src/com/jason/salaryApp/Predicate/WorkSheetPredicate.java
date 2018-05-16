package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Handler.WorkSheetFileReader;
import com.jason.salaryApp.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkSheetPredicate implements Predicate<List<String[]>>{

    @Autowired private ValidDatePredicate validDatePredicate;
    @Autowired private ValidWeekDayPredicate validWeekDayPredicate;
    @Autowired private ValidWorkSlotPredicate validWorkSlotPredicate;

    /*
    Valid WorkSheet passed from WorkSheetFileReader should be like this
    1. 15 column sheet
    2. first row: Date, 2018-4-16, 2018-4-16, 2018-4-17... 2018-4-22, 2018-4-22
    3 .Second Row: WeekDay, Mon, Mon, Tue, Tue... Sun, Sun
    4 .Each slot should have value
    5. All other rows should have Valid WorkSlot or X
     */
    @Override
    public boolean test(List<String[]> workSheet) {
        return testNonEmptySlot(workSheet)
                && testWorkSheetColumns(workSheet)
                && testWorkSheetDateRow(workSheet)
                && testWorkSheetWorkDayRow(workSheet)
                && testAllWorkSlotValid(workSheet);
    }

    boolean testNonEmptySlot(List<String[]> workSheet) {
        boolean flag = workSheet.stream()
                .allMatch(row -> !row[0].equals("X") &&
                        Arrays.stream(row)
                                .allMatch(StringUtils::isNotBlank));
        if (!flag)
            throw new IllegalArgumentException("[ERROR] Bad Input: some slot has bad value");
        return true;
    }

    private boolean testWorkSheetColumns(List<String[]> workSheet) {
        boolean flag = workSheet.stream()
                .allMatch(row -> row.length == WorkSheetFileReader.COLUMN);
        if (!flag)
            throw new IllegalArgumentException("[ERROR]: Bad Input: table should have 15 columns");
        return true;
    }

    private boolean testWorkSheetDateRow(List<String[]> workSheet) {
        boolean flag = workSheet.get(0)[0].equals("Date")
                && Arrays.stream(workSheet.get(0))
                .filter(s -> !s.equals("Date"))
                .allMatch(validDatePredicate);
        if (!flag)
            throw new IllegalArgumentException("[ERROR] Bad Date Row");
        return true;
    }

    private boolean testWorkSheetWorkDayRow(List<String[]> workSheet) {
        boolean flag = workSheet.get(1)[0].equals("WeekDay")
                && Arrays.stream(workSheet.get(0))
                .filter(s -> !s.equals("WeekDay"))
                .allMatch(validWeekDayPredicate);
        if (!flag)
            throw new IllegalArgumentException("[ERROR] Bad WeekDay Row");
        return true;
    }

    private boolean testAllWorkSlotValid(List<String[]> workSheet) {
        List<String[]> workSlotSheet = getWorkSlotSheet(workSheet);
        boolean flag = workSlotSheet.stream()
                .allMatch(row -> Arrays.stream(row)
                        .filter(s -> !s.equals("X"))
                        .allMatch(validWorkSlotPredicate)
                );
        if (!flag)
            throw new IllegalArgumentException("[ERROR] Bad Input: Some workSlot's value is wrong");
        return true;
    }

    private List<String[]> getWorkSlotSheet(List<String[]> workSheet) {
        return workSheet.subList(2, workSheet.size()).stream()
                .map(rowContent -> Arrays.copyOfRange(rowContent, 1, rowContent.length))
                .collect(Collectors.toList());
    }
}
