package graphic.timeLine;

import graphic.interfaces.BackToMenuButton;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import model.Tweet;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class TimeLineView implements MenuButton, CloseButton, BackToMenuButton {
    JTextField forwardTo = new JTextField();
    public void timeLinePage(LinkedList<Tweet> tweets){
        MainController.panel.removeAll();
        menuButton();
        backButton();
        closeButton();
        JTextField tweetText = new JTextField();
        JButton sendTweetButton= new JButton();
        JButton sendPhotoButton = new JButton();
        JLabel ft = new JLabel();

        try {
            FileReader reader = new FileReader("resources/configuration/timeLine.properties");
            Properties properties = new Properties();
            properties.load(reader);
            tweetText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),Integer.parseInt(properties.getProperty("blue"))));
            tweetText.setBounds(Integer.parseInt(properties.getProperty("tweetTextX")),
                    Integer.parseInt(properties.getProperty("tweetTextY")),
                    Integer.parseInt(properties.getProperty("tweetTextWidth")),
                    Integer.parseInt(properties.getProperty("tweetTextHeight")));
            sendTweetButton.setText(properties.getProperty("sendButton"));
            sendTweetButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendTweetButton.setBounds(Integer.parseInt(properties.getProperty("sendButtonX")),
                    Integer.parseInt(properties.getProperty("sendButtonY")),
                    Integer.parseInt(properties.getProperty("sendButtonWidth")),
                    Integer.parseInt(properties.getProperty("sendButtonHeight")));
            sendPhotoButton.setText(properties.getProperty("sendPhoto"));
            sendPhotoButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendPhotoButton.setBounds(Integer.parseInt(properties.getProperty("sendPhotoX")),
                    Integer.parseInt(properties.getProperty("sendPhotoY")),
                    Integer.parseInt(properties.getProperty("sendPhotoWidth")),
                    Integer.parseInt(properties.getProperty("sendPhotoHeight")));
            forwardTo.setBounds(Integer.parseInt(properties.getProperty("forwardToX")),
                    Integer.parseInt(properties.getProperty("forwardToY")),
                    Integer.parseInt(properties.getProperty("forwardToWidth")),
                    Integer.parseInt(properties.getProperty("forwardToHeight")));
            ft.setText(properties.getProperty("forward"));
            ft.setBounds(Integer.parseInt(properties.getProperty("ftX")),
                    Integer.parseInt(properties.getProperty("ftY")),
                    Integer.parseInt(properties.getProperty("ftWidth")),
                    Integer.parseInt(properties.getProperty("ftHeight")));

        }catch (Exception e){
            e.printStackTrace();
        }

        MainController.panel.add(tweetText);
        MainController.panel.add(sendTweetButton);
        MainController.panel.add(sendPhotoButton);
        MainController.panel.add(forwardTo);
        MainController.panel.add(ft);
        MainController.panel.setBackground(Color.lightGray);
        TweetPanelToTimeLine tweetPanelToTimeLine = new TweetPanelToTimeLine();
        tweetPanelToTimeLine.tweetPanel(tweets);
        MainController.panel.revalidate();
        MainController.panel.repaint();
        final File f[] = {null};
        sendPhotoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                f[0] = chooser.getSelectedFile();
            }

        });

        sendTweetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.tweetAgent.addTweet(MainController.username,tweetText.getText(), f[0]);
                System.out.println(f[0] != null);
                timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
            }
        });


    }
}
