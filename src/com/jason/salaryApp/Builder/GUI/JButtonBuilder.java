package com.jason.salaryApp.Builder.GUI;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Handler.SalaryAppHandler;
import com.jason.salaryApp.Utils.StringUtils;
import com.jason.salaryApp.Utils.Tools;
import com.jason.salaryApp.Writer.LogWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;

class JButtonBuilder {

    private SalaryAppHandler appHandler = new SalaryAppHandler();

    void initializeLoadButton(JButton load, JTextField fromText, JTextField toText) {
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonMessage;
                String from = fromText.getText();
                String to = toText.getText();

                SalaryCalculationInput input;
                try {
                    input = appHandler.buildCalculationInput(from, to);
                    buttonMessage = String.format("日期%s到%s, 本次有工资记录的员工人数有:%s",fromText.getText(),toText.getText(), input.getWorkSlotMap().keySet().toString());
                } catch (Exception exception) {
                    buttonMessage ="There is an ERROR, check ERROR LOG";
                    LogWriter.writeErrorLog(exception.getMessage());
                }

                JOptionPane.showMessageDialog(null, buttonMessage);
            }
        });
    }

    void initializeAllSearchButton(JButton allSearch) {
        allSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogWriter.writeSalaryLog(appHandler.calculateSalaryForAll());
                JOptionPane.showMessageDialog(null, "一键查询已完成, 请阅读工资查询记录.txt");
            }
        });
    }

    void initializeSingleSearchButton(JButton singleSearch, JTextField nameText) {
        singleSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonMessage;
                String personName = nameText.getText();

                try {
                    LogWriter.writeSalaryLog(appHandler.calculateSalaryForOne(personName));
                    buttonMessage = "查询" + personName + "的工资";
                } catch (Exception exception) {
                    buttonMessage = "There is an ERROR, check ERROR LOG";
                    LogWriter.writeErrorLog(exception.getMessage());
                }

                JOptionPane.showMessageDialog(null, buttonMessage);
            }
        });
    }
}
