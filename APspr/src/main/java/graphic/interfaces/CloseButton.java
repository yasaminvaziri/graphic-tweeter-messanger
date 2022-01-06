package graphic.interfaces;

import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public interface CloseButton {
    default void closeButton(){
        JButton closeButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/close.properties");
            Properties properties = new Properties();
            properties.load(reader);
            closeButton.setText(properties.getProperty("close"));
            closeButton.setBounds(Integer.parseInt(properties.getProperty("closeX")),
                    Integer.parseInt(properties.getProperty("closeY")),
                    Integer.parseInt(properties.getProperty("closeWidth")),
                    Integer.parseInt(properties.getProperty("closeHeight")));
            closeButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
        }catch (Exception e){
            e.printStackTrace();
        }

        //JButton closeButton = new JButton("CLOSE");
        //closeButton.setBackground(new Color(51,153,200));
        //closeButton.setBounds(500,40,185,30);
        MainController.panel.add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              MainController.frame.dispose();
            }
        });
    }

}

