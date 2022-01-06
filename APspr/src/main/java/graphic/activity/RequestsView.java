package graphic.activity;

import graphic.interfaces.BackToActivityButton;
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

public class RequestsView implements MenuButton, CloseButton, BackToActivityButton, ScrollPaneFromBottom {
    public void request(LinkedList<String> requests){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToActivity();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        JLabel warn = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/requestsView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
            warn.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        //scrollPane.setBounds(0 , 0, 500, 325);
        //JLabel warn = new JLabel();
        //warn.setBounds(520,120,185,30);
        for (String name : requests){
            JButton accept = new JButton();
            JButton reject_notify = new JButton();
            JButton reject = new JButton();
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/requestsView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                accept.setText(properties.getProperty("accept"));
                accept.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                reject_notify.setText(properties.getProperty("reject_notify"));
                reject_notify.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                reject.setText(properties.getProperty("reject"));
                reject.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),Integer.parseInt(properties.getProperty("b"))));
                nameLabel.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(name + " " + properties.getProperty("nameLabelText"));

            } catch (Exception e) {
                e.printStackTrace();
            }

            //JButton accept = new JButton("ACCEPT");
            //accept.setBackground(new Color(51,153,200));
            //JButton reject_notify = new JButton("REJECT AND NOTIFY");
            //reject_notify.setBackground(new Color(51,153,200));
            //JButton reject = new JButton("REJECT");
            //reject.setBackground(new Color(51,153,200));
            //JLabel nameLabel = new JLabel();
            //nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
            //nameLabel.setText(name + " wants to follow you");
            panel.add(nameLabel);
            panel.add(accept);
            panel.add(reject_notify);
            panel.add(reject);
            accept.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  warn.setText(MainController.logicAgent.accept(MainController.username,name,warn.getText()));
                  request(MainController.logicAgent.showRequests(MainController.username));
                }
            });
            reject.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    warn.setText(MainController.logicAgent.rejectSilently(MainController.username,name,warn.getText()));
                    request(MainController.logicAgent.showRequests(MainController.username));
                }
            });
            reject_notify.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    warn.setText(MainController.logicAgent.reject_notify(MainController.username,name,warn.getText()));
                    request(MainController.logicAgent.showRequests(MainController.username));
                }
            });


        }
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(warn);
        MainController.panel.revalidate();
        MainController.panel.repaint();
    }
}
