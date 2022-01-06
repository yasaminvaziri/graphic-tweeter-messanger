package graphic.interfaces;

import graphic.welcome.MenuView;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface MenuButton {
    default void menuButton(){
        JButton menuButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/menu.properties");
            Properties properties = new Properties();
            properties.load(reader);
            menuButton.setText(properties.getProperty("menuButton"));
            menuButton.setBounds(Integer.parseInt(properties.getProperty("menuX")),
                    Integer.parseInt(properties.getProperty("menuY")),
                    Integer.parseInt(properties.getProperty("menuWidth")),
                    Integer.parseInt(properties.getProperty("menuHeight")));
            menuButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));

        }catch (Exception e){
            e.printStackTrace();
        }

        //JButton menuButton = new JButton("MENU");
        //menuButton.setBackground(new Color(51,153,200));
        //menuButton.setBounds(500,0,185,30);
        MainController.panel.add(menuButton);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuView menuView = new MenuView();
                menuView.menuPage();
            }
        });
    }
}
