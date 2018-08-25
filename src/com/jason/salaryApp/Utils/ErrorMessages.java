package com.jason.salaryApp.Utils;

import lombok.Getter;

@Getter
public class ErrorMessages {
    public static final String EMPTY_SALARY_ROW = "[ERROR]Bad Salary Input: there exists empty row, please delete";
    public static final String BAD_SALARY_COLUMN = "[ERROR]Bad Salary Input: salary file should have 2 columns";
    public static final String BAD_SALARY_NUMBER = "[ERROR]Bad Salary Input: some salary Amount is not right, please correct";
    public static final String NULL_WORKSLOT_VALUE = "[ERROR]Bad WorkSheet Input: this workSlot's value is wrong:";
    public static final String BAD_WORKSHEET_COLUMN ="[ERROR]Bad WorkSheet Input: table should have 15 columns";
    public static final String BAD_DATE_ROW ="[ERROR]Bad WorkSheet Date Row";
    public static final String BAD_WEEKDAY_ROW ="[ERROR]Bad WorkSheet Weekday Row";
    public static final String BAD_WORKSLOT_VALUE ="[ERROR]Bad WorkSheet Input: some workSlot value of this row may be wrong:";
    public static final String ROW_EXCEED_COLUMN = "[ERROR]This row has 15+ columns:";
    public static final String MISS_SALARY_KEY = "[ERROR]Some people in workSheet are not in SalaryFile -> ";
    public static final String WRONG_INPUT_PERSON = "[ERROR]This person is not in workSheet";
    public static final String WRONG_INPUT_DATE_FORMAT = "[ERROR]Input Date format should be yyyy-MM-dd";
}