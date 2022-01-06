package graphic.activity;

import graphic.interfaces.*;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class ActivityView implements MenuButton, CloseButton, BackToHomeButton, ScrollPaneFromBottom {
    public void activity(LinkedList<String> followed_you){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        backToHome();
        JButton requests = new JButton();
        JButton rejected_from = new JButton();
        JButton your_requests = new JButton();
        JButton unfollowed_you = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try(FileReader reader = new FileReader("resources/configuration/activityView.properties")){
            Properties properties = new Properties();
            properties.load(reader);
            requests.setText(properties.getProperty("requests"));
            requests.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            requests.setBounds(Integer.parseInt(properties.getProperty("requestsX")),
                    Integer.parseInt(properties.getProperty("requestsY")),
                    Integer.parseInt(properties.getProperty("requestsWidth")),
                    Integer.parseInt(properties.getProperty("requestsHeight")));
            rejected_from.setText(properties.getProperty("rejected_from"));
            rejected_from.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            rejected_from.setBounds(Integer.parseInt(properties.getProperty("rejected_fromX")),
                    Integer.parseInt(properties.getProperty("rejected_fromY")),
                    Integer.parseInt(properties.getProperty("rejected_fromWidth")),
                    Integer.parseInt(properties.getProperty("rejected_fromHeight")));
            your_requests.setText(properties.getProperty("your_requests"));
            your_requests.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            your_requests.setBounds(Integer.parseInt(properties.getProperty("your_requestsX")),
                    Integer.parseInt(properties.getProperty("your_requestsY")),
                    Integer.parseInt(properties.getProperty("your_requestsWidth")),
                    Integer.parseInt(properties.getProperty("your_requestsHeight")));
            unfollowed_you.setText(properties.getProperty("unFollowed_you"));
            unfollowed_you.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            unfollowed_you.setBounds(Integer.parseInt(properties.getProperty("unFollowed_youX")),
                    Integer.parseInt(properties.getProperty("unFollowed_youY")),
                    Integer.parseInt(properties.getProperty("unFollowed_youWidth")),
                    Integer.parseInt(properties.getProperty("unFollowed_youHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //JButton requests = new JButton("REQUESTS");
        //requests.setBackground(new Color(51,153,200));
        //requests.setBounds(500,170,185,30);
        //JButton rejected_from = new JButton("REJECTED FROM:");
        //rejected_from.setBackground(new Color(51,153,200));
        //rejected_from.setBounds(500,210,185,30);
        //JButton your_requests = new JButton( "REQUESTED TO:");
        //your_requests.setBackground(new Color(51,153,200));
        //your_requests.setBounds(500,255,185,30);
        //JButton unfollowed_you = new JButton("UNFOLLOWED FROM:");
        //unfollowed_you.setBackground(new Color(51,153,200));
        //unfollowed_you.setBounds(500,295,185,30);
        //JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        //JScrollPane scrollPane = new JScrollPane(panel);
        //scrollToBottom(scrollPane);
        //scrollPane.setBounds(0 , 0, 500, 325);
        for (String names : followed_you){
            JLabel nameLabel = new JLabel();
            try {
                FileReader reader = new FileReader("resources/configuration/activityView.properties");
                Properties properties = new Properties();
                properties.load(reader);
                nameLabel.setFont(new Font(properties.getProperty("fontName"),
                        Font.PLAIN,Integer.parseInt(properties.getProperty("fontSize"))));
                nameLabel.setText(names + " " + properties.getProperty("nameLabelText") );
            }catch (Exception e){
                e.printStackTrace();
            }
            //nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
            //nameLabel.setText(names + " is following you ");
            panel.add(nameLabel);
        }
        unfollowed_you.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnfollowedFrom unfollowedFrom = new UnfollowedFrom();
                unfollowedFrom.unfollowedFrom(MainController.logicAgent.showUnfollowedFrom(MainController.username));
            }
        });
        requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestsView requestsView = new RequestsView();
                requestsView.request(MainController.logicAgent.showRequests(MainController.username));
            }
        });
        your_requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RequestedToView requestedToView = new RequestedToView();
                requestedToView.requestTo(MainController.logicAgent.showSentRequest(MainController.username));
            }
        });
        rejected_from.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RejectedFromView rejectedFromView = new RejectedFromView();
                rejectedFromView.rejectedFrom(MainController.logicAgent.showRejectedFrom(MainController.username));
            }
        });

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(unfollowed_you);
        MainController.panel.add(your_requests);
        MainController.panel.add(rejected_from);
        MainController.panel.add(requests);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }
}
