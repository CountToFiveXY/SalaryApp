package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Builder.CalculationInputBuilder;
import com.jason.salaryApp.Calculate.SalaryCalculator;
import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Predicate.ValidSalaryCalculationInputPredicate;
import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Utils.Tools;

import java.nio.file.NoSuchFileException;

public class SalaryAppHandler {
    /*
    This handler contains three API GUI calls
     */

    private SalaryCalculationInput calculationInput;

    private ValidSalaryCalculationInputPredicate salaryCalculationInputPredicate = new ValidSalaryCalculationInputPredicate();

    private CalculationInputBuilder inputBuilder = new CalculationInputBuilder();
    private SalaryCalculator salaryCalculator = new SalaryCalculator();

    //build workSlotMap and SalaryMap separately and build Calculation Input with them.
    public SalaryCalculationInput buildCalculationInput(String startDateString, String endDateString) throws NoSuchFileException{
        calculationInput = inputBuilder.buildCalculationInput(startDateString, endDateString);
        Tools.checkArgument(salaryCalculationInputPredicate.test(calculationInput), ErrorMessages.MISS_SALARY_KEY + salaryCalculationInputPredicate.getUnSalariedPeopleName(calculationInput));
        return calculationInput;
    }

    public String calculateSalaryForAll() {
        return salaryCalculator.calculateForAll(calculationInput);
    }

    public String calculateSalaryForOne(String personName) {
        Tools.checkArgument(calculationInput.getWorkSlotMap().containsKey(personName), ErrorMessages.WRONG_INPUT_PERSON);
        return salaryCalculator.calculateSalaryForOnePerson(calculationInput, personName, true);
    }
}
