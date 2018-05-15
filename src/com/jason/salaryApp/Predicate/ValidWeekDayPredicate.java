package com.jason.salaryApp.Predicate;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class ValidWeekDayPredicate implements Predicate<String>{
    List<String> weekDayList = Arrays.asList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");

    @Override
    public boolean test(String dateString) {
        return weekDayList.contains(dateString);
    }
}
