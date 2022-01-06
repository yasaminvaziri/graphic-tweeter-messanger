package graphic.timeLine;



import graphic.comment.CommentView;
import graphic.profile.ShowProfileView;
import graphic.interfaces.ScrollPaneFromBottom;
import logic.FileImage;
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

import static graphic.photo.Photo.scaleFileImage;


public class TweetPanelToTimeLine extends TimeLineView implements ScrollPaneFromBottom{

    public void tweetPanel(LinkedList<Tweet> tweets) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);
        try {
            FileReader reader = new FileReader("resources/configuration/tweets.properties");
            Properties properties = new Properties();
            properties.load(reader);
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scrollPaneX")),
                    Integer.parseInt(properties.getProperty("scrollPaneY")),
                    Integer.parseInt(properties.getProperty("scrollPaneWidth")),
                    Integer.parseInt(properties.getProperty("scrollPaneHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        for (Tweet tweet: tweets) {
            JLabel photo = new JLabel();
            photo.setIcon(scaleFileImage(FileImage.loadImage(tweet.getUser().getUsername())));
            JLabel label = new JLabel(), labelPhoto = new JLabel();
            label.setText(tweet.getT());
            File file = FileImage.loadTweetPhoto(tweet.getUser().getUsername(),tweet.getId());
            if (file != null)
                labelPhoto.setIcon(scaleFileImage(file));
            JButton like = new JButton();
            JButton save = new JButton();
            JButton disLike = new JButton();
            JButton report = new JButton();
            JButton forward = new JButton();
            JButton seeP = new JButton();
            JButton comment = new JButton();
            JButton retweet = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/tweets.properties");
                Properties properties = new Properties();
                properties.load(reader);
                label.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN,
                        Integer.parseInt(properties.getProperty("fontSize"))));
                like.setText(properties.getProperty("like"));
                like.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                save.setText(properties.getProperty("save"));
                save.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                disLike.setText(properties.getProperty("dislike"));
                disLike.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                report.setText(properties.getProperty("report"));
                report.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                forward.setText(properties.getProperty("forward"));
                forward.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                seeP.setText(properties.getProperty("profile"));
                seeP.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                comment.setText(properties.getProperty("comment"));
                comment.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                retweet.setText(properties.getProperty("retweet"));
                retweet.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));

            }catch (Exception e){
                e.printStackTrace();
            }
            panel.add(photo);
            panel.add(label);
            panel.add(labelPhoto);
            panel.add(like);
            panel.add(save);
            panel.add(report);
            panel.add(disLike);
            panel.add(forward);
            panel.add(retweet);
            panel.add(comment);
            panel.add(seeP);

            comment.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CommentView commentView = new CommentView();
                    LinkedList<Tweet> backs = new LinkedList<>();
                    backs.add(tweet);
                    commentView.commentPage(MainController.tweetAgent.idToTweet(tweet.getCommentList()),tweet, backs);
                }
            });

            seeP.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ShowProfileView showProfileView = new ShowProfileView();
                    showProfileView.showProfile(tweet.getUser().getUsername());
                }
            });

            like.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.tweetAgent.likeTweet(tweet,MainController.username);
                }
            });

            disLike.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.tweetAgent.dislikeTweet(tweet,MainController.username);
                }
            });

            report.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.tweetAgent.reportTweet(tweet,MainController.username);
                }
            });

            retweet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.tweetAgent.retweet(tweet,MainController.username);
                    timeLinePage(MainController.tweetAgent.showTweet(MainController.username));
                }
            });

            forward.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    MainController.messageAgent.forwardTweet(MainController.username,forwardTo.getText(),tweet);

                }
            });

            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.messageAgent.addTweetToSaved(tweet.getUser().getUsername(),tweet);
                }
            });

        }
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.revalidate();
        MainController.panel.repaint();




    }
}
