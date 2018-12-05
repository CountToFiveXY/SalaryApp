package com.jason.salaryApp;

import com.jason.salaryApp.Builder.GUI.GUIBuilder;

public class Main {

    /*
    Design Review:
    Input: 1. worksheet csv(3) 2. salarySheet txt(1)

    Process:
    3 csv --> three raw worksheet data --> validation check --> three valid raw worksheet data --> three workSheet map --> filter by calculation date --> combine to one workSlot Map
    1 salary txt --> salary Sheet data --> salary Map

    UI:
    load button --> build calculationInput(workSlot Map and Salary Map)
    calculation button --> operation on calculationInput to create log
    */

    public static void main(String[] args) {
        GUIBuilder guiBuilder = new GUIBuilder();
        guiBuilder.buildGUI();
    }
}