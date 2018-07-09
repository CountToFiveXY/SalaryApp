package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Data.WorkSlot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Component
public class WorkSlotsMapCombiner {

    public HashMap<String, List<WorkSlot>> combineWorkSlotsMap(List<HashMap<String, List<WorkSlot>>> workSlotMaps) {
        HashMap<String, List<WorkSlot>> combinedMap = new HashMap<>();
        for (HashMap<String, List<WorkSlot>> workSlotMap : workSlotMaps) {
            for (String personName : workSlotMap.keySet()) {
                if (!combinedMap.containsKey(personName)) {
                    combinedMap.put(personName, new ArrayList<>());
                }
                List<WorkSlot> workSlotsToBeMerged = workSlotMap.get(personName);
                combinedMap.get(personName).addAll(workSlotsToBeMerged);
            }
        }
        sortMap(combinedMap);
        return combinedMap;
    }

    private void sortMap(HashMap<String, List<WorkSlot>> combineMap) {
        for (List<WorkSlot> workSlots : combineMap.values()) {
            workSlots.sort(Comparator.comparing(WorkSlot::getDate));
        }
    }
}
