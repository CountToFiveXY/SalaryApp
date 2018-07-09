package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WorkSlotsMapCombinerTest {

    private static final String JASON = "Jason";
    private static final String WENDY = "Wendy";
    private final WorkSlot workSlot1 = new WorkSlot("11－4", "2018-05-11", "Mon");
    private final WorkSlot workSlot2 = new WorkSlot("9:30－5", "2018-05-12", "Tue");
    private final WorkSlot workSlot3 = new WorkSlot("5－11", "2018-05-16", "Tue");
    private final List<WorkSlot> workSlotsForJason1 = Arrays.asList(workSlot2, workSlot1);
    private final List<WorkSlot> workSlotsForJason2 = Arrays.asList(workSlot3);
    private final List<WorkSlot> workSlotsForWendy = Arrays.asList(workSlot3, workSlot2);

    private final HashMap<String, List<WorkSlot>> workSlotMap1 = new HashMap<>();
    private final HashMap<String, List<WorkSlot>> workSlotMap2 = new HashMap<>();
    private final List<HashMap<String, List<WorkSlot>>> workSlotMaps = new ArrayList<>();
    private WorkSlotsMapCombiner combiner;

    @Before
    public void setUp() {
        workSlotMap1.put(JASON, workSlotsForJason1);
        workSlotMap1.put(WENDY, workSlotsForWendy);
        workSlotMap2.put(JASON, workSlotsForJason2);
        workSlotMaps.add(workSlotMap1);
        workSlotMaps.add(workSlotMap2);
        combiner = new WorkSlotsMapCombiner();
    }

    @Test
    public void testCombineWorkSlotsMap() {
        HashMap<String, List<WorkSlot>> result;
        result = combiner.combineWorkSlotsMap(workSlotMaps);
        printMap(result);
    }

    private void printMap(HashMap<String, List<WorkSlot>> map) {
        Tools.print("[INFO]From WorkSlotsMapCombiner Unit Test: ");
        map.forEach((personName, workSlots) -> {
            Tools.print(personName + "->" + workSlots.toString());
        });
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}