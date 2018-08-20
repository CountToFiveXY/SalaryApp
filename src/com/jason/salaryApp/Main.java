package com.jason.salaryApp;

import com.jason.salaryApp.Builder.GUI.GUIBuilder;

public class Main {

    /*
    Design Review:
    Input: 1. 3 worksheet csvs
           2. 1 salarySheet txt

    Process: workSheetInput ---workSheetFileReader read each sheet---> three raw worksheet data ---> filter By validWorkSheetPredicate(If Any Sheet invalid, break whole program) ---|
    ******************************                  SalaryAppHandler merge three maps to one WorkSlotMap <---WorkSlotsMapBuilder build map for each data--- three valid raw worksheet data <---|
     */

    public static void main(String[] args) {
        GUIBuilder guiBuilder = new GUIBuilder();
        guiBuilder.buildGUI();
    }
}