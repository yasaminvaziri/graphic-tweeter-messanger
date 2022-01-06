package graphic.comment;

import graphic.comment.CommentToTimeLine;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import graphic.timeLine.TimeLineView;
import model.Tweet;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class CommentView implements MenuButton, CloseButton {
    JTextField forwardTo = new JTextField();
    public void commentPage(LinkedList<Tweet> comments, Tweet t, LinkedList<Tweet> backs){
        MainController.panel.removeAll();
        menuButton();
        closeButton();
        JTextField commentText = new JTextField();
        JButton sendButton = new JButton();
        JButton backButton = new JButton();
        JLabel ft = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/tweets.properties");
            Properties properties = new Properties();
            properties.load(reader);
            commentText.setBackground(new Color(Integer.parseInt(properties.getProperty("textFieldR")),
                    Integer.parseInt(properties.getProperty("textFieldG")),
                    Integer.parseInt(properties.getProperty("textFieldB"))));
            commentText.setBounds(Integer.parseInt(properties.getProperty("textFieldX")),
                    Integer.parseInt(properties.getProperty("textFieldY")),
                    Integer.parseInt(properties.getProperty("textFieldWidth")),
                    Integer.parseInt(properties.getProperty("textFieldHeight")));
            sendButton.setText(properties.getProperty("send"));
            sendButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendButton.setBounds(Integer.parseInt(properties.getProperty("sendX")),
                    Integer.parseInt(properties.getProperty("sendY")),
                    Integer.parseInt(properties.getProperty("sendWidth")),
                    Integer.parseInt(properties.getProperty("sendHeight")));
            backButton.setText(properties.getProperty("back"));
            backButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            backButton.setBounds(Integer.parseInt(properties.getProperty("backX")),
                    Integer.parseInt(properties.getProperty("backY")),
                    Integer.parseInt(properties.getProperty("backWidth")),
                    Integer.parseInt(properties.getProperty("backHeight")));
            forwardTo.setBounds(Integer.parseInt(properties.getProperty("forwardToX")),
                    Integer.parseInt(properties.getProperty("forwardToY")),
                    Integer.parseInt(properties.getProperty("forwardToWidth")),
                    Integer.parseInt(properties.getProperty("forwardToHeight")));
            ft.setText(properties.getProperty("forwardL"));
            ft.setBounds(Integer.parseInt(properties.getProperty("ftX")),
                    Integer.parseInt(properties.getProperty("ftY")),
                    Integer.parseInt(properties.getProperty("ftWidth")),
                    Integer.parseInt(properties.getProperty("ftHeight")));


        }catch (Exception e){
            e.printStackTrace();
        }
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(backButton);
        MainController.panel.add(commentText);
        MainController.panel.add(sendButton);
        MainController.panel.add(forwardTo);
        MainController.panel.add(ft);
        CommentToTimeLine commentToTimeLine = new CommentToTimeLine();
        commentToTimeLine.commentPanel(comments, backs);
        MainController.panel.revalidate();
        MainController.panel.repaint();


        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.tweetAgent.addComment(MainController.username,commentText.getText(),t);
                commentPage(MainController.tweetAgent.idToTweet(t.getCommentList()), t, backs);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backs.removeLast();
                if (!backs.isEmpty())
                    commentPage(MainController.tweetAgent.idToTweet( backs.getLast().getCommentList()), backs.getLast(), backs);
                else {
                    TimeLineView timeLineView = new TimeLineView();
                    timeLineView.timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
                }
            }
        });
    }
}
