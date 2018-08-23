package com.jason.salaryApp.Builder;

public class SalaryEasterEggBuilder {

    public String easterEgg1 (double sum) {
        if (sum > 800.00)
            return "(这么多钱你好意思要吗,上交给静文20%吧.)";
        else if (sum > 400.00)
            return "(不错,你上班时间及格了.攒点钱可以讨老婆/老公了.)";
        else if (sum > 150)
            return "(你个穷逼,还不赶紧干活挣钱.)";
        else
            return "(要你何用.)";
    }

    public String easterEgg2 (double sum) {
        //彩蛋2
        if (sum > 800.00)
            return "(土豪请客)";
        else if (sum > 400.00)
            return "(你及格了)";
        else if (sum > 150)
            return "(穷逼一个)";
        else
            return "(要你何用)";
    }
}
