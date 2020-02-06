package com.jason.salaryApp.Builder.GUI;

import com.jason.salaryApp.Data.SalaryCalculationInput;
import com.jason.salaryApp.Handler.SalaryAppHandler;
import com.jason.salaryApp.Utils.ErrorMessages;
import com.jason.salaryApp.Writer.LogWriter;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;

class JButtonFunctionBuilder {

    private SalaryAppHandler salaryAppHandler = new SalaryAppHandler();

    void initializeLoadButton(JButton load, JTextField fromText, JTextField toText) {
        load.addActionListener((ActionEvent e) -> {
                String buttonMessage;
                String from = fromText.getText();
                String to = toText.getText();

                SalaryCalculationInput input;
                try {
                    input = salaryAppHandler.buildCalculationInput(from, to);
                    buttonMessage = String.format("工时%s到%s, 本次有工资记录的员工人数有:%s", fromText.getText(), toText.getText(), input.getWorkSlotMap().keySet().toString());
                } catch (Exception exception) {
                    buttonMessage = ErrorMessages.BUTTON_ERROR_MESSAGE;
                    LogWriter.writeErrorLog(exception.getMessage());
                }

                JOptionPane.showMessageDialog(null, buttonMessage);
            }
        );
    }

    void initializeAllSearchButton(JButton allSearch) {
        allSearch.addActionListener((ActionEvent e) -> {
                LogWriter.writeSalaryLog(salaryAppHandler.calculateSalaryForAll());
                JOptionPane.showMessageDialog(null, "一键查询已完成, 请阅读工资查询记录");
            }
        );
    }

    void initializeSingleSearchButton(JButton singleSearch, JTextField nameText) {
        singleSearch.addActionListener((ActionEvent e) -> {
                String buttonMessage;
                String personName = nameText.getText();

                try {
                    LogWriter.writeSalaryLog(salaryAppHandler.calculateSalaryForOne(personName));
                    buttonMessage = "查询" + personName + "的工资";
                } catch (Exception exception) {
                    buttonMessage = ErrorMessages.BUTTON_ERROR_MESSAGE;;
                    LogWriter.writeErrorLog(exception.getMessage());
                }

                JOptionPane.showMessageDialog(null, buttonMessage);
            }
        );
    }

    void initializeTipSearchButton(JButton singleSearch, JTextField nameText) {
        singleSearch.addActionListener((ActionEvent e) -> {
                    String buttonMessage;
                    String tipNumber = nameText.getText();

                    try {
                        LogWriter.writeSalaryLog(salaryAppHandler.calculateTipForAll(tipNumber));
                        buttonMessage = "分摊总计" + tipNumber + "的小费";
                    } catch (Exception exception) {
                        buttonMessage = ErrorMessages.BUTTON_ERROR_MESSAGE;;
                        LogWriter.writeErrorLog(exception.getMessage());
                    }

                    JOptionPane.showMessageDialog(null, buttonMessage);
                }
        );
    }
}
