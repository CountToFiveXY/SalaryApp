package com.jason.salaryApp.Utils;

import org.junit.Test;

public class ToolsTest {

    @Test
    public void testCheckArgumentWithRightArgument() {
        String personName = "JingWen";
        Tools.checkArgument(personName.endsWith("n"), "Name was wrong");
    }

    @Test
    public void testCheckArgumentWithWrongArgument() {
        String personName = "JingWen";
        Tools.checkArgument(personName.endsWith("J"), "Name was wrong");
    }
}
