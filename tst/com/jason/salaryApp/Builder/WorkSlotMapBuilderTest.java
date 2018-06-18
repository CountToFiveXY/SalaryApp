package com.jason.salaryApp.Builder;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Reader.WorkSheetFileReader;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public class WorkSlotMapBuilderTest {
    private final WorkSheetFileReader reader = new WorkSheetFileReader();
    private final WorkSlotsMapBuilder builder = new WorkSlotsMapBuilder();

    @Test
    public void testSetUpWorkSlotMapForEachSheet() {
        List<String[]> input = getInputTable("1.csv");
        HashMap<String, List<WorkSlot>> result = builder.buildWorkSlotMapForEachSheet(input);
        printMap(result);
    }

    private List<String[]>  getInputTable(String fileName) {
        return reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + fileName);
    }

    private void printMap(HashMap<String, List<WorkSlot>> map) {
        Tools.print("[INFO]From WorkSlotsMapBuilder Unit Test: ");
        map.forEach((personName, workSlots) -> {
            Tools.print(personName + "->" + workSlots.toString());
        });
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}