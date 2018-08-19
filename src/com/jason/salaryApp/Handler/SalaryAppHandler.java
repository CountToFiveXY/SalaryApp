package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Builder.CalculationInputBuilder;
import com.jason.salaryApp.Calculate.SalaryCalculator;
import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Predicate.ValidSalaryCalculationInputPredicate;
import com.jason.salaryApp.Utils.Tools;

import java.nio.file.NoSuchFileException;

public class SalaryAppHandler {

    private SalaryCalculationInput calculationInput;

    private ValidSalaryCalculationInputPredicate salaryCalculationInputPredicate = new ValidSalaryCalculationInputPredicate();

    private CalculationInputBuilder inputBuilder = new CalculationInputBuilder();
    private SalaryCalculator salaryCalculator = new SalaryCalculator();

    //build workSlotMap and SalaryMap separately and build Calculation Input with them.
    public void buildCalculationInput(String startDateString, String endDateString) throws NoSuchFileException{
        calculationInput = inputBuilder.buildCalculationInput(startDateString, endDateString);
    }

    public String calculateSalary() {
        Tools.checkArgument(salaryCalculationInputPredicate.test(calculationInput), "Some people in workSheet are not in SalaryFile -> " + salaryCalculationInputPredicate.getUnSalariedPeopleName(calculationInput));
        salaryCalculator.calculate(calculationInput);
        return salaryCalculator.log;
    }
}
