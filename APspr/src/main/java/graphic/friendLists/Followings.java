package graphic.friendLists;

import graphic.friendLists.category.CategoryView;
import graphic.interfaces.BackToFriendsButton;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import graphic.interfaces.ScrollPaneFromBottom;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class Followings implements BackToFriendsButton, MenuButton, CloseButton, ScrollPaneFromBottom {

    public void followings(LinkedList<String> followings){
        MainController.panel.removeAll();
        backToFriends();
        menuButton();
        closeButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/friends.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPane2X")),
                    Integer.parseInt(properties.getProperty("scrollPane2Y")),
                    Integer.parseInt(properties.getProperty("scrollPane2Width")),
                    Integer.parseInt(properties.getProperty("scrollPane2Height")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //scrollPane.setBounds(0 , 0, 200, 300);
        for (String names : followings){
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/friends.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(names);
            }catch (Exception e){
                e.printStackTrace();
            }
            //nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
            //nameLabel.setText(names);
            panel.add(nameLabel);
        }
        JButton categories = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/friends.properties");
            Properties properties = new Properties();
            properties.load(reader);
            categories.setText(properties.getProperty("categories"));
            categories.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            categories.setBounds(Integer.parseInt(properties.getProperty("categoriesX")),
                    Integer.parseInt(properties.getProperty("categoriesY")),
                    Integer.parseInt(properties.getProperty("categoriesWidth")),
                    Integer.parseInt(properties.getProperty("categoriesHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        //JButton categories = new JButton("CATEGORIES");
        //categories.setBackground(new Color(51,153,200));
        //categories.setBounds(220,0,150,30);

        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryView category = new CategoryView();
                category.categoryPage(MainController.categoryAgent.showGroups(MainController.username));
            }
        });



        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(categories);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }
}
