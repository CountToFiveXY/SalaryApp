package com.jason.salaryApp.Calculate;

import com.jason.salaryApp.Utils.Tools;

public abstract class Calculator {
    double totalWorkHour = 0.0;

    void AddToLogBuilder(String logString, StringBuilder sb) {
        sb.append(logString + Tools.LOG_SEPARATOR);
    }

    void resetTotalWorkHour() {
        totalWorkHour = 0.0;
    }
}
