package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WorkSlotsMapFilterTest {
    private static final String JASON = "Jason";
    private static final String WENDY = "Wendy";
    private final WorkSlot workSlot1 = new WorkSlot("11－4", "2018-05-10", "Mon");
    private final WorkSlot workSlot2 = new WorkSlot("9:30－5", "2018-05-12", "Tue");
    private final WorkSlot workSlot3 = new WorkSlot("5－11", "2018-05-16", "Tue");
    private final List<WorkSlot> workSlotsForJason = Arrays.asList(workSlot1, workSlot2);
    private final List<WorkSlot> workSlotsForWendy = Arrays.asList(workSlot2, workSlot3);

    private final HashMap<String, List<WorkSlot>> workSlotMap = new HashMap<>();
    private WorkSlotsMapFilter filter;

    @Before
    public void setUp() {
        workSlotMap.put(JASON, workSlotsForJason);
        workSlotMap.put(WENDY, workSlotsForWendy);
        filter = new WorkSlotsMapFilter();
    }

    @Test
    public void filterWorkSlotsMap() {
        String startDate = "2018-05-01";
        String endDate = "2018-05-15";
        filter.filterWorkSlotsMap(workSlotMap, startDate, endDate);
        printMap(workSlotMap);
    }

    private void printMap(HashMap<String, List<WorkSlot>> map) {
        Tools.print("[INFO]From WorkSlotsMapFilter Unit Test: ");
        map.forEach((personName, workSlots) -> {
            Tools.print(personName + "->" + workSlots.toString());
        });
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}