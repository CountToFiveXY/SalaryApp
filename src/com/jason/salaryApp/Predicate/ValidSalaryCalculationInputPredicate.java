package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Data.SalaryCalculationInput;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ValidSalaryCalculationInputPredicate implements Predicate<SalaryCalculationInput> {

    @Override
    public boolean test(SalaryCalculationInput input) {
        return getUnSalariedPeopleName(input).isEmpty();
    }

    public List<String> getUnSalariedPeopleName(SalaryCalculationInput input) {
        Set<String> workPeopleSet = input.getWorkSlotMap().keySet();
        Set<String> salariedPeopleSet = input.getSalaryMap().keySet();
        return workPeopleSet.stream()
                .filter(name -> !salariedPeopleSet.contains(name))
                .collect(Collectors.toList());
    }
}
