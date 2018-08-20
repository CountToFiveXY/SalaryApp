package com.jason.salaryApp.Builder.GUI;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Handler.SalaryAppHandler;
import com.jason.salaryApp.Utils.Tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;

public class JButtonBuilder {

    private SalaryAppHandler appHandler = new SalaryAppHandler();

    public void initializeLoadButton(JButton load, JTextField fromText, JTextField toText) {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonMessage = "";
                String from = fromText.getText();
                String to = toText.getText();

                if (!from.endsWith("-") && !to.endsWith("-")) {
                    buttonMessage = String.format("Loading Data from %s to %s.",fromText.getText(),toText.getText());
                } else {
                    buttonMessage = "[Error]: Your Date format is wrong.";
                }

                JOptionPane.showMessageDialog(null, buttonMessage);

                SalaryCalculationInput input = new SalaryCalculationInput(new HashMap<>(), new HashMap<>());
                try {
                    input = appHandler.buildCalculationInput(from, to);
                } catch (NoSuchFileException exception) {
                    Tools.print("Some File is Missing: " + exception);
                }
                Tools.print("本次有工资记录的员工人数有: "+ input.getWorkSlotMap().keySet().toString());
            }
        });
    }
}
