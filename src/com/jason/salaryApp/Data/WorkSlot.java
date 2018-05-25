package com.jason.salaryApp.Data;

import com.jason.salaryApp.Utils.StringUtils;
import lombok.Data;

@Data
public class WorkSlot {
    private final String fromTime;
    private final String toTime;
    private final String Date;
    private final String WeekDay;

    public WorkSlot(String timeString, String Date, String WeekDay) {
        String[] time = StringUtils.convertWorkSlotString(timeString);
        this.fromTime = time[0];
        this.toTime = time[1];
        this.Date = Date;
        this.WeekDay = WeekDay;
    }
}
