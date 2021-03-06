package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Reader.WorkSheetFileReader;
import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ValidWorkTablePredicate {

    /*
    Valid WorkSheet passed from WorkSheetFileReader should be like this
    1. 15 column sheet
    2. first row: Date, 2018-04-16, 2018-04-16, 2018-04-17... 2018-04-22, 2018-04-23
    3 .Second Row: WeekDay, Mon, Mon, Tue, Tue... Sun, Sun
    4 .Each slot should have value(Empty slot is shown as X)
    5. All other rows should not start with X and must have valid workSlot String.
     */

    public boolean test(List<String[]> workSheet) {
        return testNonEmptySlot(workSheet)
                && testWorkSheetColumns(workSheet)
                && testWorkSheetDateRow(workSheet)
                && testWorkSheetWorkDayRow(workSheet)
                && testAllWorkSlotValid(workSheet);
    }

    private boolean testNonEmptySlot(List<String[]> workSheet) {
        workSheet.forEach(row -> {
            boolean flag = !row[0].equals("X") &&
                    Arrays.stream(row)
                            .allMatch(StringUtils::isNotBlank);
            if (!flag)
                throw new IllegalArgumentException(ErrorMessages.NULL_WORKSLOT_VALUE + Arrays.toString(row) + ", File Date(" + getFirstDate(workSheet) + ")");
        });

        return true;
    }

    private boolean testWorkSheetColumns(List<String[]> workSheet) {
        boolean flag = workSheet.stream()
                .allMatch(row -> row.length == WorkSheetFileReader.COLUMN_NUM);
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.BAD_WORKSHEET_COLUMN);
        return true;
    }

    private boolean testWorkSheetDateRow(List<String[]> workSheet) {
        boolean flag = workSheet.get(0)[0].equals("Date")
                && Arrays.stream(workSheet.get(0))
                .filter(s -> !s.equals("Date"))
                .allMatch(new ValidDatePredicate());
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.BAD_DATE_ROW + Arrays.asList(workSheet.get(0)));
        return true;
    }

    private boolean testWorkSheetWorkDayRow(List<String[]> workSheet) {
        boolean flag = workSheet.get(1)[0].equals("WeekDay")
                && Arrays.stream(workSheet.get(1))
                .filter(s -> !s.equals("WeekDay"))
                .allMatch(new ValidWeekDayPredicate());
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.BAD_WEEKDAY_ROW + Arrays.asList(workSheet.get(0)));
        return true;
    }

    private boolean testAllWorkSlotValid(List<String[]> workSheet) {
        List<String[]> workSlotSheet = getWorkSlotSheet(workSheet);

        for (String[] row : workSlotSheet) {
            boolean f = Arrays.stream(row).filter(s -> !s.equals("X")).allMatch(new ValidWorkSlotPredicate());
            if (!f)
                throw new IllegalArgumentException(ErrorMessages.BAD_WORKSLOT_VALUE + Arrays.toString(row) + ", File Date(" + getFirstDate(workSheet) + ")");
        }

        return true;
    }

    private List<String[]> getWorkSlotSheet(List<String[]> workSheet) {
        return workSheet.subList(2, workSheet.size()).stream()
                .map(rowContent -> Arrays.copyOfRange(rowContent, 1, rowContent.length))
                .collect(Collectors.toList());
    }

    private String getFirstDate(List<String[]> workSheet) {
        return workSheet.get(0)[1];
    }
}
