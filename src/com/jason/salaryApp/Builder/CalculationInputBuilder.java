package com.jason.salaryApp.Builder;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Handler.WorkSlotsMapCombiner;
import com.jason.salaryApp.Handler.WorkSlotsMapFilter;
import com.jason.salaryApp.Predicate.ValidSalarySheetPredicate;
import com.jason.salaryApp.Predicate.ValidWorkTablePredicate;
import com.jason.salaryApp.Reader.SalaryFileReader;
import com.jason.salaryApp.Reader.WorkSheetFileReader;

import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CalculationInputBuilder {

    //two predicate
    private ValidWorkTablePredicate workSheetPredicate = new ValidWorkTablePredicate();
    private ValidSalarySheetPredicate salarySheetPredicate = new ValidSalarySheetPredicate();

    //two reader
    private WorkSheetFileReader workSheetFileReader = new WorkSheetFileReader();
    private SalaryFileReader salaryFileReader = new SalaryFileReader();

    //builder and filter
    private WorkSlotsMapBuilder workSlotsMapBuilder = new WorkSlotsMapBuilder();
    private WorkSlotsMapFilter workSlotsMapFilter = new WorkSlotsMapFilter();
    private WorkSlotsMapCombiner workSlotsMapCombiner = new WorkSlotsMapCombiner();
    private SalaryMapBuilder salaryMapBuilder = new SalaryMapBuilder();

    //build workSlotMap and SalaryMap separately and build Calculation Input with them.
    public SalaryCalculationInput buildCalculationInput(String startDateString, String endDateString) throws NoSuchFileException {
        return new SalaryCalculationInput(getWorkSlotsMap(startDateString, endDateString), getSalaryMap());
    }

    private HashMap<String, List<WorkSlot>> getWorkSlotsMap(String startDateString, String endDateString) {
        List<HashMap<String, List<WorkSlot>>> workSlotMaps = getValidWorkSheets()
                .stream()
                .map(workSheet -> workSlotsMapBuilder.buildWorkSlotMapForEachSheet(workSheet))
                .collect(Collectors.toList());
        workSlotMaps.forEach(workSlotMap -> workSlotsMapFilter.filterOnlyWorkSlotsMap(workSlotMap, startDateString, endDateString));

        return workSlotsMapCombiner.combineWorkSlotsMap(workSlotMaps);
    }

    private HashMap<String, Float> getSalaryMap() throws NoSuchFileException{
        List<String[]> salarySheet = salaryFileReader.getFormalSalaryFile();
        if (!salarySheetPredicate.test(salarySheet)) {
            return null;
        }
        return salaryMapBuilder.buildSalaryMap(salarySheet);
    }

    private List<List<String[]>> getValidWorkSheets() {
        return workSheetFileReader.getFormalFilesPath()
                .stream()
                .map(workSheetFileReader::readWorkSheetFile)
                .filter(this::isValidWorkSheet)
                .collect(Collectors.toList());
    }

    private boolean isValidWorkSheet(List<String[]> workSheet) {
        return workSheetPredicate.test(workSheet);
    }
}
