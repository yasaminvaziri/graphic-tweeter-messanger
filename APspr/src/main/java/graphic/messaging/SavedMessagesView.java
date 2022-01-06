package graphic.messaging;

import graphic.interfaces.BackToMessagingButton;
import graphic.interfaces.CloseButton;
import graphic.interfaces.MenuButton;
import graphic.interfaces.ScrollPaneFromBottom;
import model.messaging.Message;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Properties;

public class SavedMessagesView implements BackToMessagingButton, MenuButton, CloseButton, ScrollPaneFromBottom {
    public void savedMessages(LinkedList<Message> messages){
        MainController.panel.removeAll();
        menuButton();
        backToMessaging();
        closeButton();
        JTextField messageText = new JTextField();
        JButton sendButton = new JButton();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollToBottom(scrollPane);


        try {
            FileReader reader = new FileReader("resources/configuration/savedMessages.properties");
            Properties properties = new Properties();
            properties.load(reader);
            messageText.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            messageText.setBounds(Integer.parseInt(properties.getProperty("mtX")),
                    Integer.parseInt(properties.getProperty("mtY")),
                    Integer.parseInt(properties.getProperty("mtWidth")),
                    Integer.parseInt(properties.getProperty("mtHeight")));
            sendButton.setText(properties.getProperty("send"));
            sendButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            sendButton.setBounds(Integer.parseInt(properties.getProperty("sendX")),
                    Integer.parseInt(properties.getProperty("sendY")),
                    Integer.parseInt(properties.getProperty("sendWidth")),
                    Integer.parseInt(properties.getProperty("sendHeight")));
            scrollPane.setBounds(Integer.parseInt(properties.getProperty("scX")),
                    Integer.parseInt(properties.getProperty("scY")),
                    Integer.parseInt(properties.getProperty("scWidth")),
                    Integer.parseInt(properties.getProperty("scHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }

        //messageText.setBackground(new Color(255,204,51));
        //messageText.setBounds(0,545,500,120);
        ///JButton sendButton = new JButton("SEND");
        //sendButton.setBackground(new Color(51,153,200));
        //sendButton.setBounds(500,545,185,120);
        //JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
       // JScrollPane scrollPane = new JScrollPane(panel);
        //scrollToBottom(scrollPane);
        //scrollPane.setBounds(0 , 0, 500, 544);
        for (Message message : messages){
            JLabel label = new JLabel();
            JButton edit = new JButton();
            JButton delete = new JButton();
            try {
                FileReader reader = new FileReader("resources/configuration/savedMessages.properties");
                Properties properties = new Properties();
                properties.load(reader);
                label.setFont(new Font(properties.getProperty("fontName"),Font.PLAIN
                        ,Integer.parseInt(properties.getProperty("fontSize"))));
                label.setText(MainController.username+ ":" + message.showM(MainController.username));
                edit.setText(properties.getProperty("edit"));
                edit.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
                delete.setText(properties.getProperty("delete"));
                delete.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                        Integer.parseInt(properties.getProperty("g")),
                        Integer.parseInt(properties.getProperty("b"))));
            }catch (Exception e){
                e.printStackTrace();
            }

            //label.setFont(new Font("Serif", Font.PLAIN, 22));
            //label.setText(MainController.username+ ":" + message.showM(MainController.username));
           // JButton edit = new JButton("EDIT");
            //edit.setBackground(new Color(51,153,200));
            //JButton delete = new JButton("DELETE");
            //delete.setBackground(new Color(51,153,200));
            panel.add(label);
            panel.add(edit);
            panel.add(delete);

            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  MainController.messageAgent.deleteSavedMessage(MainController.username,message);
                  savedMessages(MainController.messageAgent.showSavedMessages(MainController.username));
                }
            });
            edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainController.messageAgent.editSavedMessage(message,messageText.getText());
                    label.setText(MainController.username + ":" + messageText.getText());
                }
            });


        }
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainController.messageAgent.addMessageToSaved(MainController.username,messageText.getText());
                savedMessages(MainController.messageAgent.showSavedMessages(MainController.username));
            }
        });



        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainController.panel.add(scrollPane);
        MainController.panel.add(messageText);
        MainController.panel.add(sendButton);
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.revalidate();
        MainController.panel.repaint();

    }

}
