package com.jason.salaryApp.Builder;

public class SalaryEasterEggBuilder {

    public static String easterEgg (double sum) {
        //彩蛋
        if (sum > 500.00)
            return "(土豪请客)";
        else if (sum > 300.00)
            return "(再接再厉)";
        else if (sum > 160)
            return "(你及格了)";
        else if (sum == 0.0)
            return "(这人没干活)";
        else
            return "(你辞职吧)";
    }
}
