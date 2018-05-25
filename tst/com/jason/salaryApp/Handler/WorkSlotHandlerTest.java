package com.jason.salaryApp.Handler;


import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class WorkSlotHandlerTest {
    private WorkSlotHandler handler = new WorkSlotHandler();
    private final WorkSheetFileReader reader = new WorkSheetFileReader();

    @Test
    public void testSetUpWorkSlotMapForEachSheet() {
        List<String[]> input = getInputTable("1.csv");
        HashMap<String, List<WorkSlot>> result = handler.setUpWorkSlotMapForEachSheet(input);
        printMap(result);
    }

    private List<String[]>  getInputTable(String fileName) {
        return reader.readWorkSheetFile(WorkSheetFileReader.TEST_WORKSHEET_FILE_PATH + fileName);
    }

    private void printMap(HashMap<String, List<WorkSlot>> map) {
        Tools.print("\n[INFO]From WorkSlotHandler Unit Test: ");
        map.forEach((personName, workSlots) -> {
            Tools.print(personName + workSlots.toString());
        });
    }
}