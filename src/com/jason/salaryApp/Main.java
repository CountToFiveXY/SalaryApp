package com.jason.salaryApp;

import com.jason.salaryApp.Builder.GUI.GUIBuilder;

public class Main {

    /*
    Design Review:
    Input: 1. 3 worksheet csvs
           2. 1 salarySheet txt

    Process:
    3 csvs --> three raw worksheet data --> sheet check --> three valid raw worksheet data --> three workSlot map --> filter by calculation date --> combine to one workSlot Map
    1 salary txt --> salary Sheet data --> salary Map

    load button two maps --> calculationInput
    calculation button --> operate on Input

     */

    public static void main(String[] args) {
        GUIBuilder guiBuilder = new GUIBuilder();
        guiBuilder.buildGUI();
    }
}