package graphic.welcome;

import graphic.explorer.ExplorerView;
import graphic.home.HomePageView;
import graphic.interfaces.CloseButton;
import graphic.messaging.MessagingView;
import graphic.setting.SettingView;
import graphic.timeLine.TimeLineView;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class  MenuView implements CloseButton {


    public void menuPage(){
        MainController.panel.removeAll();
        JButton homeButton = new JButton();
        JButton timeLineButton = new JButton();
        JButton explorerButton = new JButton();
        JButton settingButton = new JButton();
        JButton messageButton = new JButton();
        try {
            FileReader reader = new FileReader("resources/configuration/menuView.properties");
            Properties properties = new Properties();
            properties.load(reader);
            homeButton.setText(properties.getProperty("home"));
            homeButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            homeButton.setBounds(Integer.parseInt(properties.getProperty("homeX")),
                    Integer.parseInt(properties.getProperty("homeY")),
                    Integer.parseInt(properties.getProperty("homeWidth")),
                    Integer.parseInt(properties.getProperty("homeHeight")));
            timeLineButton.setText(properties.getProperty("timeLine"));
            timeLineButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            timeLineButton.setBounds(Integer.parseInt(properties.getProperty("timeLineX")),
                    Integer.parseInt(properties.getProperty("timeLineY")),
                    Integer.parseInt(properties.getProperty("timeLineWidth")),
                    Integer.parseInt(properties.getProperty("timeLineHeight")));
            explorerButton.setText(properties.getProperty("explorer"));
            explorerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            explorerButton.setBounds(Integer.parseInt(properties.getProperty("explorerX")),
                    Integer.parseInt(properties.getProperty("explorerY")),
                    Integer.parseInt(properties.getProperty("explorerWidth")),
                    Integer.parseInt(properties.getProperty("explorerHeight")));
            settingButton.setText(properties.getProperty("setting"));
            settingButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            settingButton.setBounds(Integer.parseInt(properties.getProperty("settingX")),
                    Integer.parseInt(properties.getProperty("settingY")),
                    Integer.parseInt(properties.getProperty("settingWidth")),
                    Integer.parseInt(properties.getProperty("settingHeight")));
            messageButton.setText(properties.getProperty("message"));
            messageButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            messageButton.setBounds(Integer.parseInt(properties.getProperty("messageX")),
                    Integer.parseInt(properties.getProperty("messageY")),
                    Integer.parseInt(properties.getProperty("messageWidth")),
                    Integer.parseInt(properties.getProperty("messageHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }




        //JButton homeButton = new JButton("HOME");
        //homeButton.setBackground(new Color(51,153,200));
        //homeButton.setBounds(225,100,200,30);
        //JButton timeLineButton = new JButton("TIMELINE");
        //timeLineButton.setBackground(new Color(51,153,200));
        //timeLineButton.setBounds(225,200,200,30);
        //JButton explorerButton = new JButton("EXPLORER");
        //explorerButton.setBackground(new Color(51,153,200));
       // explorerButton.setBounds(225,300,200,30);
        //JButton settingButton = new JButton("SETTING");
        //settingButton.setBackground(new Color(51,153,200));
        //settingButton.setBounds(225,400,200,30);
        //JButton messageButton = new JButton("MESSAGING");
        //messageButton.setBackground(new Color(51,153,200));
        //messageButton.setBounds(225,500,200,30);
        closeButton();
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(homeButton);
        MainController.panel.add(timeLineButton);
        MainController.panel.add(explorerButton);
        MainController.panel.add(settingButton);
        MainController.panel.add(messageButton);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePageView homePageView = new HomePageView();
                homePageView.homePage();
            }
        });


        timeLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeLineView timeLineView = new TimeLineView();
                timeLineView.timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
            }
        });


        explorerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ExplorerView explorerView = new ExplorerView();
               explorerView.explorerPage(MainController.tweetAgent.discover(MainController.username));
            }
        });


        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingView settingView = new SettingView();
                settingView.settingPage();
            }
        });


        messageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessagingView messagingView = new MessagingView();
                messagingView.messagingView();
            }
        });
    }

}
