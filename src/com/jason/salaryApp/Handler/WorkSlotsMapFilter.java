package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import com.jason.salaryApp.Utils.Tools;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkSlotsMapFilter {

    public void filterWorkSlotsMap(HashMap<String, List<WorkSlot>> workSlotsMap, String startDateString, String endDateString) {
        for (String personName : workSlotsMap.keySet()) {
            workSlotsMap.put(personName, filterWorkSlotsBasedOnSalaryPeriod(workSlotsMap.get(personName), startDateString, endDateString));
        }
    }

    private List<WorkSlot> filterWorkSlotsBasedOnSalaryPeriod(List<WorkSlot> workSlots, String startDateString, String endDateString) {
        return workSlots.stream()
                .filter(workSlot -> Tools.isBetweenTwoDays(workSlot.getDate(), startDateString, endDateString))
                .collect(Collectors.toList());
    }
}
