package graphic.interfaces;


import graphic.friendLists.Friends;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface BackToFriendsButton {
    default void backToFriends(){
        JButton backButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/backs.properties");
            Properties properties = new Properties();
            properties.load(reader);
            backButton.setText(properties.getProperty("back"));
            backButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            backButton.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //JButton backButton = new JButton("BACK");
        //backButton.setBounds(500,80,185,30);
        //backButton.setBackground(new Color(51,153,200));
        MainController.panel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friends friends = new Friends();
                friends.friendsPage(MainController.logicAgent.showFollowers(MainController.username));

            }
        });
    }
}
