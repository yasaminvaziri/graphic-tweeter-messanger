package graphic.activity;

import graphic.interfaces.BackToActivityButton;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import graphic.interfaces.ScrollPaneFromBottom;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class UnfollowedFrom implements CloseButton, MenuButton, ScrollPaneFromBottom, BackToActivityButton {
    public void unfollowedFrom(LinkedList<String> unfollowed){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToActivity();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try (FileReader reader = new FileReader("resources/configuration/activityButtons.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //scrollPane.setBounds(0 , 0, 500, 325);
        for (String name : unfollowed){
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/unfollowedYou.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(name + " " + properties.getProperty("nameLabelText"));

            }catch (Exception e){
                e.printStackTrace();
            }
            //nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
            //nameLabel.setText(name + " unfollowed you ");
            panel.add(nameLabel);
        }


        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();
    }
}
