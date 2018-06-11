package com.jason.salaryApp.Handler;

import org.junit.Test;

import java.nio.file.NoSuchFileException;

public class SalaryAppHandlerTest {
    private SalaryAppHandler handler;

    @Test
    public void buildCalculationInput() throws NoSuchFileException{
        handler.buildCalculationInput();
    }

    @Test
    public void calculateSalary() {
        handler.calculateSalary();
    }
}