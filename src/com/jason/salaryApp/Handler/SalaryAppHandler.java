package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Builder.SalaryMapBuilder;
import com.jason.salaryApp.Builder.WorkSlotMapBuilder;
import com.jason.salaryApp.Calculate.SalaryCalculator;
import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Predicate.ValidSalarySheetPredicate;
import com.jason.salaryApp.Predicate.ValidWorkTablePredicate;
import com.jason.salaryApp.Reader.SalaryFileReader;
import com.jason.salaryApp.Reader.WorkSheetFileReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SalaryAppHandler {

    private SalaryCalculationInput calculationInput;
    @Autowired private ValidWorkTablePredicate workSheetPredicate;
    @Autowired private ValidSalarySheetPredicate salarySheetPredicate;

    @Autowired private WorkSheetFileReader workSheetFileReader;
    @Autowired private SalaryFileReader salaryFileReader;

    @Autowired private WorkSlotMapBuilder workSlotMapBuilder;
    @Autowired private SalaryMapBuilder salaryMapBuilder;

    @Autowired private SalaryCalculator salaryCalculator;

    //build workSlotMap and SalaryMap separately and build Calculation Input with them.
    public void buildCalculationInput() throws NoSuchFileException{
        calculationInput = new SalaryCalculationInput(getWorkSlotsMap(), getSalaryMap());
    }

    public void calculateSalary() {
        salaryCalculator.calculate(calculationInput);
    }

    private HashMap<String, List<WorkSlot>> getWorkSlotsMap() {
        List<List<String[]>> workSheets = getWorkSheets();
        //TODO: Add Logic here
        return new HashMap<>();
    }

    private List<List<String[]>> getWorkSheets() {
        return workSheetFileReader.getFormalFilesPath()
                .stream()
                .map(workSheetFileReader::readWorkSheetFile)
                .filter(this::isValidWorkSheet)
                .collect(Collectors.toList());
    }

    private HashMap<String, Float> getSalaryMap() throws NoSuchFileException{
        if (!salarySheetPredicate.test(salaryFileReader.getFormalSalaryFile())) {
            return null;
        }
        return salaryMapBuilder.buildSalaryMap(salaryFileReader.getFormalSalaryFile());
    }

    private boolean isValidWorkSheet(List<String[]> workSheet) {
       return workSheetPredicate.test(workSheet);
    }
}
