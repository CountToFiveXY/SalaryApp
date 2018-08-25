package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Utils.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ValidSalarySheetPredicate implements Predicate<List<String[]>> {

    @Override
    public boolean test(List<String[]> salarySheet) {
        return testNonEmptySlot(salarySheet)
                && testSalarySheetColumns(salarySheet)
                && testSalaryStrings(salarySheet);
    }

    private boolean testNonEmptySlot(List<String[]> salarySheet) {
        boolean flag = salarySheet.stream()
                .allMatch(row -> Arrays.stream(row)
                                .allMatch(StringUtils::isNotBlank));
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.EMPTY_SALARY_ROW);
        return true;
    }

    private boolean testSalarySheetColumns(List<String[]> workSheet) {
        boolean flag = workSheet.stream()
                .allMatch(row -> row.length == 2);
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.BAD_SALARY_COLUMN);
        return true;
    }

    private boolean testSalaryStrings(List<String[]> salarySheet) {
        boolean flag = salarySheet.stream()
                .allMatch(row -> StringUtils.isFloat(row[1]));
        if (!flag)
            throw new IllegalArgumentException(ErrorMessages.BAD_SALARY_NUMBER);
        return true;
    }
}
