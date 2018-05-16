package com.jason.salaryApp.Predicate;

import java.util.List;
import java.util.function.Predicate;

public class ValidSalarySheetPredicate implements Predicate<List<String[]>> {

    @Override
    public boolean test(List<String[]> salarySheet) {
        return true;
    }
}
