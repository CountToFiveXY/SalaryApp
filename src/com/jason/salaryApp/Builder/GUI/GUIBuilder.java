package com.jason.salaryApp.Builder.GUI;

import com.jason.salaryApp.Handler.ImageHandler;
import com.jason.salaryApp.Utils.Tools;

import javax.swing.*;
import java.awt.*;

public class GUIBuilder {

    //App的参数
    private final static int APP_WIDTH = 250;
    private final static int APP_HEIGHT = 205;
    private final static String KFT_VERSION_STRING  = " KFT工资计算器v3.1";
    private final static String AUTHOR_LABEL = "        ~~ Developed By @Jason ~~";
    private final static String LABEL_START_DATE = "开始日期:";
    private final static String LABEL_END_DATE = "结束日期:";
    private final static String LABEL3 = "员工姓名:";
    private final static String LOAD_BUTTON = "加载表格";
    private final static String ALL_SEARCH_BUTTON = "一键查询";
    private final static String SINGLE_SEARCH_BUTTON = "查询";
    private final static String ORIGINAL_IMAGE_PATH = "resources/1.jpg";

    private JFrame jf;
    private JPanel jpanel;
    private ImageHandler imageHandler = new ImageHandler(ORIGINAL_IMAGE_PATH);
    private JButtonBuilder buttonBuilder = new JButtonBuilder();

    public void buildGUI() {
        jf = new JFrame(KFT_VERSION_STRING);
        jpanel = (JPanel)jf.getContentPane();

        buildGUIWithBGImage();
        setUpJPanel();

        //create 4 labels
        createAuthorLabel();
        createLable(LABEL_START_DATE, new Font("Dialog",0,13),30, 35, 70, 30);
        createLable(LABEL_END_DATE, new Font("Dialog",0,13),30, 70, 70, 30);
        createLable(LABEL3, new Font("Dialog",0,13),30, 145, 70, 30);

        //三个输入框, 前两个显示当前的日期
        final JTextField fromText, toText, nameText;
        fromText = createTextWindow(Tools.getLocalTime(),95,35,125,28);
        toText = createTextWindow(Tools.getLocalTime(),95,70,125,28);
        nameText = createTextWindow(null,95,145,60,30);

        //三个按钮
        JButton load, allSearch, singleSearch;
        load = createButton(LOAD_BUTTON, Color.white, 20, 102, 100, 35);
        buttonBuilder.initializeLoadButton(load, fromText, toText);
        allSearch = createButton(ALL_SEARCH_BUTTON, Color.white, 125,102,100,35);
        buttonBuilder.initializeAllSearchButton(allSearch);
        singleSearch = createButton(SINGLE_SEARCH_BUTTON, Color.white, 165,144,60,30);
        buttonBuilder.initializeSingleSearchButton(singleSearch, nameText);

        setUpJFrame();
    }

    private void buildGUIWithBGImage() {
        //背景图片处理
        JLabel bgLabel = imageHandler.createBackGroundImageLabel();
        jf.getLayeredPane().add(bgLabel, new Integer(Integer.MIN_VALUE));
    }

    private void setUpJPanel() {
        jpanel.setLayout(null);
        jpanel.setBounds(0,0,APP_WIDTH,APP_HEIGHT);
        jpanel.setOpaque(false);
    }

    private JLabel createLable(String labelName, Font font, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelName);
        label.setFont(font);
        ConfigLabel(label,x,y,width,height);
        return label;
    }

    private void createAuthorLabel() {
        JLabel authorLabel = createLable(AUTHOR_LABEL, new Font("Times New Roman",1,14), 0,7,250,21);
        authorLabel.setBackground(Color.YELLOW);
        authorLabel.setForeground(Color.BLUE);
        authorLabel.setOpaque(true);
    }

    private void ConfigLabel(JLabel label, int x, int y, int width, int height) {
        label.setBounds(x, y, width, height);
        jpanel.add(label);
    }

    private JButton createButton(String buttonName, Color color, int x, int y, int width, int height) {
        JButton button = new JButton(buttonName);
        button.setBackground(color);
        button.setOpaque(true);
        setButton(button, x, y, width, height);
        return button;
    }

    private void setButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        jpanel.add(button);
    }

    private JTextField createTextWindow(String text, int x, int y, int width, int height) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        jpanel.add(textField);
        return textField;
    }

    private void setUpJFrame() {
        jf.setLayout(null);
        jf.setBounds(0,0,APP_WIDTH,APP_HEIGHT);
        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
