package com.jason.salaryApp.Exceptions;

public class WrongDateFormatException extends RuntimeException{

    public WrongDateFormatException (String message) {
        super (message);
    }
}
