package com.jason.salaryApp.Predicate;

import com.jason.salaryApp.Utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ValidWorkSlotPredicate implements Predicate<String> {

    @Override
    public boolean test(String workSlot) {
        return StringUtils.convertWorkSlotString(workSlot).length == 2;
    }
}
