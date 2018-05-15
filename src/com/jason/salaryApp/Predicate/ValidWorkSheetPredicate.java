package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Handler.FileHandler;
import com.jason.salaryApp.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidWorkSheetPredicate implements Predicate<List<String[]>>{

    @Autowired private ValidDatePredicate validDatePredicate;
    @Autowired private ValidWeekDayPredicate validWeekDayPredicate;
    @Autowired private ValidWorkSlotPredicate validWorkSlotPredicate;

    @Override
    public boolean test(List<String[]> workSheet) {
        return testNonEmptySlot(workSheet)
                && testWorkSheetColumns(workSheet)
                && testWorkSheetDateRow(workSheet)
                && testWorkSheetWorkDayRow(workSheet)
                && testAllWorkSlotValid(workSheet);
    }

    boolean testNonEmptySlot(List<String[]> workSheet) {
        return workSheet.stream()
                .allMatch(row -> !row[0].equals("X") &&
                        Arrays.stream(row)
                                .allMatch(StringUtils::isNotBlank));
    }

    boolean testWorkSheetColumns(List<String[]> workSheet) {
        return workSheet.stream()
                .allMatch(row -> row.length == FileHandler.COLUMN);
    }

    private boolean testWorkSheetDateRow(List<String[]> workSheet) {
        return workSheet.get(0)[0].equals("Date")
                && Arrays.stream(workSheet.get(0))
                .filter(s -> !s.equals("Date"))
                .allMatch(validDatePredicate);
    }

    private boolean testWorkSheetWorkDayRow(List<String[]> workSheet) {
        return workSheet.get(1)[0].equals("WeekDay")
                && Arrays.stream(workSheet.get(0))
                .filter(s -> !s.equals("WeekDay"))
                .allMatch(validWeekDayPredicate);
    }

    private boolean testAllWorkSlotValid(List<String[]> workSheet) {
        List<String[]> workSlotSheet = getWorkSlotSheet(workSheet);
        return workSlotSheet.stream()
                .allMatch(row -> Arrays.stream(row)
                        .filter(s -> !s.equals("X"))
                        .allMatch(validWorkSlotPredicate)
                );
    }

    private List<String[]> getWorkSlotSheet(List<String[]> workSheet) {
        return workSheet.subList(2, workSheet.size()).stream()
                .map(rowContent -> Arrays.copyOfRange(rowContent, 1, rowContent.length))
                .collect(Collectors.toList());
    }
}
