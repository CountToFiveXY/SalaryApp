package com.jason.salaryApp.Data;

import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
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
        Tools.checkArgument(workTime > 0, ErrorMessages.BAD_WORKSLOT_TIME + getWorkInfo());
        return workTime;
    }

    public String toLog() {
        return String.format("Date: %s, workTime: %s--%s(%.1f hours)", getDate(), getFromTime(), getToTime(), getWorkTime());
    }

    private String getWorkInfo() {
        return String.format("%s - %s", fromTime, toTime);
    }
}
