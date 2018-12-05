package com.jason.salaryApp.Builder;

import com.jason.salaryApp.Utils.StringUtils;

import com.jason.salaryApp.Utils.ErrorMessages;
import java.util.HashMap;
import java.util.List;

class SalaryMapBuilder {

    HashMap<String, Float> buildSalaryMap(List<String[]> salaryContent) {
        HashMap<String, Float> salaryMap = new HashMap<>();
        salaryContent.forEach(rowContent -> {
            String personName = rowContent[0];
            Float salaryPerHour = StringUtils.toFloat(rowContent[1]);
            fillSalaryMap(salaryMap, personName, salaryPerHour);
        });
        return salaryMap;
    }

    private void fillSalaryMap(HashMap<String, Float> salaryMap, String personName, Float salary) {
        if (salaryMap.containsKey(personName)) {
            throw new IllegalArgumentException(ErrorMessages.MULTIPLE_SALARY_LINE_MESSAGE);
        }
        salaryMap.put(personName, salary);
    }
}
