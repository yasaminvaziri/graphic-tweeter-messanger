package graphic.friendLists.category;

import graphic.friendLists.Followings;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import graphic.interfaces.ScrollPaneFromBottom;
import model.Group;
import util.MainController;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class CategoryMembersView implements MenuButton, CloseButton, ScrollPaneFromBottom {
    public void categoryMembers(LinkedList<String> names, Group group){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        JPanel panel = new JPanel();
        JButton back = new JButton();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        JLabel label = new JLabel();
        JTextField catName = new JTextField();
        JButton addButton = new JButton();


        try {
            FileReader reader = new FileReader("resources/configuration/categoryView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            back.setText(properties.getProperty("back"));
            back.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            back.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
            label.setText(properties.getProperty("label2"));
            label.setBounds(Integer.parseInt(properties.getProperty("label2X")),
                    Integer.parseInt(properties.getProperty("label2Y")),
                    Integer.parseInt(properties.getProperty("label2Width")),
                    Integer.parseInt(properties.getProperty("label2Height")));
            catName.setBounds(Integer.parseInt(properties.getProperty("catNameX")),
                    Integer.parseInt(properties.getProperty("catNameY")),
                    Integer.parseInt(properties.getProperty("catNameWidth")),
                    Integer.parseInt(properties.getProperty("catNameHeight")));
            catName.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            addButton.setText(properties.getProperty("addButton"));
            addButton.setBounds(Integer.parseInt(properties.getProperty("addButtonX")),
                    Integer.parseInt(properties.getProperty("addButtonY")),
                    Integer.parseInt(properties.getProperty("addButtonWidth")),
                    Integer.parseInt(properties.getProperty("addButtonHeight")));
            addButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
        }catch (Exception e){
            e.printStackTrace();
        }

        //JButton back = new JButton("BACK");
        //back.setBounds(500,80,185,30);
        //back.setBackground(new Color(51,153,200));
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //JScrollPane scrollPane = new JScrollPane(panel);
        //scrollToBottom(scrollPane);
        //scrollPane.setBounds(0 , 0, 200, 300);
        //JLabel label = new JLabel("write a name to add:");
        //label.setBounds(210,0,150,30);
        //JTextField catName = new JTextField();
        //catName.setBounds(210,34,150,30);
        //catName.setBackground(new Color(255,204,51));
        //JButton addButton = new JButton("ADD");
        //addButton.setBackground(new Color(51,153,200));
        //addButton.setBounds(370,34,70,30);
        for (String name : names ){
            JLabel usernameLabel = new JLabel();
            JButton removeUsername = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/categoryView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                usernameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                usernameLabel.setText(name);
                removeUsername.setText(properties.getProperty("removeUsername"));
                removeUsername.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
            }catch (Exception e){
                e.printStackTrace();
            }

            //usernameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
            //usernameLabel.setText(name);
            //JButton removeUsername = new JButton("remove username");
            //removeUsername.setBackground(new Color(51,153,200));
            panel.add(usernameLabel);
            panel.add(removeUsername);

            removeUsername.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                 MainController.categoryAgent.deleteName(MainController.username,name,group);
                 categoryMembers(MainController.categoryAgent.showMembers(MainController.username,group),group);
                }
            });
        }
            addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              MainController.categoryAgent.addToGroup(MainController.username,catName.getText(),group);
              categoryMembers(MainController.categoryAgent.showMembers(MainController.username,group),group);
            }
            });

           back.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   CategoryView categoryView = new CategoryView();
                   categoryView.categoryPage(MainController.categoryAgent.showGroups(MainController.username));
               }
           });


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(back);
        MainController.panel.add(addButton);
        MainController.panel.add(catName);
        MainController.panel.add(label);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();




    }
}
