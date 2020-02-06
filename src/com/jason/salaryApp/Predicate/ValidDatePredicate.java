package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Utils.StringUtils;
import org.springframework.stereotype.Component;
import java.util.function.Predicate;

@Component
public class ValidDatePredicate implements Predicate<String>{

    @Override
    public boolean test(String dateString) {
        String[] date = StringUtils.convertDateString(dateString);

        return date.length == 3
                && testMonth(date[1])
                && testDay(date[2])
                && testYear(date[0]);
    }

    private boolean testYear(String year) {
        return year.startsWith("2");
    }

    private boolean testMonth(String month) {
        return StringUtils.toInteger(month) <= 12;
    }

    private boolean testDay(String day) {
        return StringUtils.toInteger(day) <= 31;
    }
}
