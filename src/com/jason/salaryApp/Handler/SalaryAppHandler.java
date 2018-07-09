package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Builder.SalaryMapBuilder;
import com.jason.salaryApp.Builder.WorkSlotsMapBuilder;
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

    @Autowired private WorkSlotsMapBuilder workSlotsMapBuilder;
    @Autowired private WorkSlotsMapFilter workSlotsMapFilter;
    @Autowired private WorkSlotsMapCombiner workSlotsMapCombiner;
    @Autowired private SalaryMapBuilder salaryMapBuilder;

    @Autowired private SalaryCalculator salaryCalculator;

    //build workSlotMap and SalaryMap separately and build Calculation Input with them.
    public void buildCalculationInput(String startDateString, String endDateString) throws NoSuchFileException{
        calculationInput = new SalaryCalculationInput(getWorkSlotsMap(startDateString, endDateString), getSalaryMap());
    }

    public void calculateSalary() {
        salaryCalculator.calculate(calculationInput);
    }

    private HashMap<String, List<WorkSlot>> getWorkSlotsMap(String startDateString, String endDateString) {
        List<HashMap<String, List<WorkSlot>>> workSlotMaps = getValidWorkSheets()
                .stream()
                .map(workSheet -> workSlotsMapBuilder.buildWorkSlotMapForEachSheet(workSheet))
                .collect(Collectors.toList());
        workSlotMaps.forEach(workSlotMap -> workSlotsMapFilter.filterWorkSlotsMap(workSlotMap, startDateString, endDateString));

        return workSlotsMapCombiner.combineWorkSlotsMap(workSlotMaps);
    }


    private List<List<String[]>> getValidWorkSheets() {
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
