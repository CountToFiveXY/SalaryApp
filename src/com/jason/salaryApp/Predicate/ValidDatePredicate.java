package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Utils.StringUtils;
import org.springframework.stereotype.Component;
import java.util.function.Predicate;

@Component
public class ValidDatePredicate implements Predicate<String>{

    @Override
    public boolean test(String dateString) {
        return StringUtils.convertDateString(dateString).length == 3;
    }
}
