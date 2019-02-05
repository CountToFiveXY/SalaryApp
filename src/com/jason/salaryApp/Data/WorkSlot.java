package com.jason.salaryApp.Data;

import com.jason.salaryApp.Utils.StringUtils;
import lombok.Data;

@Data
public class WorkSlot {
    private final String Date;
    private final String WeekDay;
    private final String fromTime;
    private final String toTime;

    public WorkSlot(String timeString, String Date, String WeekDay) {
        String[] time = StringUtils.convertWorkSlotString(timeString);
        this.fromTime = time[0];
        this.toTime = time[1];
        this.Date = Date;
        this.WeekDay = WeekDay;
    }

    public double getWorkTime() {
        float from = StringUtils.convertTimeToNumberFormat(fromTime);
        float to = StringUtils.convertTimeToNumberFormat(toTime);
        double workTime = to - from;
        //assume a workTime is longer than 3 hours
        return workTime > 0 ? workTime : workTime + 12;
    }

    public String toLog() {
        return String.format("Date: %s, workTime: %s--%s(%.1f hours)", getDate(), getFromTime(), getToTime(), getWorkTime());
    }
}
