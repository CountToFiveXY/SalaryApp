package com.jason.salaryApp.Handler;

import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import org.junit.Test;

import java.nio.file.NoSuchFileException;
import java.util.Arrays;

public class SalaryAppHandlerTest {
    private SalaryAppHandler handler = new SalaryAppHandler();

    @Test
    public void buildCalculationInput() throws NoSuchFileException{
        String startDate = "2018-07-20";
        String endDate = "2018-07-30";
        handler.buildCalculationInput(startDate, endDate);
        String log = handler.calculateSalaryForAll();
        printLog(log);
    }

    private void printLog(String logString) {
        Tools.print("[INFO]From SalaryAppHandler Unit Test: ");
        String[] divideLog = StringUtils.convertLogString(logString);
        Arrays.stream(divideLog).forEach(Tools::print);
        System.out.println("-----------TEST SEPARATE LINE-----------");
    }
}