package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import java.util.Set;
import java.util.function.Predicate;

public class ValidSalaryCalculationInputPredicate implements Predicate<SalaryCalculationInput> {

    @Override
    public boolean test(SalaryCalculationInput input) {
        return checkAllPersonSalaried(input);
    }

    private boolean checkAllPersonSalaried(SalaryCalculationInput input) {
        Set<String> workPeopleSet = input.getWorkSlotMap().keySet();
        Set<String> salariedPeopleSet = input.getSalaryMap().keySet();
        return workPeopleSet.stream()
                .allMatch(personName -> salariedPeopleSet.contains(personName));
    }
}
