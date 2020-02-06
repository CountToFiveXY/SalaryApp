package com.jason.salaryApp.Utils;

import lombok.Getter;

@Getter
public class ErrorMessages {
    //SalaryError
    public static final String EMPTY_SALARY_ROW = "[ERROR]Bad Salary Input: there exists empty row, please delete.";
    public static final String BAD_SALARY_COLUMN = "[ERROR]Bad Salary Input: salary file should have 2 columns.";
    public static final String BAD_SALARY_NUMBER = "[ERROR]Bad Salary Input: some salary Amount is not right, please correct.";
    public static final String MULTIPLE_SALARY_LINE_MESSAGE = "[ERROR]Multiple salary line for one person: ";
    public static final String MISS_SALARY_KEY = "[ERROR]Some people in workSheet are not in SalaryFile, please add -> ";

    //WorkSlot Error
    public static final String NULL_WORKSLOT_VALUE = "[ERROR]Bad WorkSheet Input: this workSlot's value is wrong: ";
    public static final String BAD_WORKSLOT_TIME = "[ERROR]Bad WorkSlot time: workSlot must has positive workTime: ";
    public static final String BAD_WORKSHEET_COLUMN ="[ERROR]Bad WorkSheet Input: table should have 8 columns.";
    public static final String BAD_DATE_ROW = "[ERROR]Bad WorkSheet Date Row from: ";
    public static final String BAD_WEEKDAY_ROW = "[ERROR]Bad WorkSheet Weekday Row from: ";
    public static final String BAD_WORKSLOT_VALUE = "[ERROR]Bad WorkSheet Input: some workSlot value of this row may be wrong: ";
    public static final String ROW_EXCEED_COLUMN = "[ERROR]This row has 8+ columns: ";

    //Input Error
    public static final String WRONG_INPUT_PERSON = "[ERROR]This person is not in workSheet: ";
    public static final String WRONG_INPUT_DATE_FORMAT = "[ERROR]Wrong Input Date format: ";
    public static final String WRONG_INPUT_TIP = "[ERROR]Input Tip should be a number!";
    public static final String BUTTON_ERROR_MESSAGE = "There is an ERROR, check ERROR LOG.";
}
