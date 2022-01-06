package graphic.welcome;

import graphic.interfaces.CloseButton;
import logic.RegisterAgent;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class RegisterView implements CloseButton {


    public void registerPage(){
        MainController.panel.removeAll();
        JLabel userLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel phoneLabel = new JLabel();
        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JButton registerButton = new JButton();
        JCheckBox showPassword = new JCheckBox();
        JLabel warnLabel = new JLabel();



        try {
            FileReader reader = new FileReader("resources/configuration/register.properties");
            Properties properties = new Properties();
            properties.load(reader);
            userLabel.setText(properties.getProperty("userLabel"));
            userLabel.setBounds(Integer.parseInt(properties.getProperty("userLabelX")),
                    Integer.parseInt(properties.getProperty("userLabelY")),
                    Integer.parseInt(properties.getProperty("userLabelWidth")),
                    Integer.parseInt(properties.getProperty("userLabelHeight")));
            passwordLabel.setText(properties.getProperty("passLabel"));
            passwordLabel.setBounds(Integer.parseInt(properties.getProperty("passLabelX")),
                    Integer.parseInt(properties.getProperty("passLabelY")),
                    Integer.parseInt(properties.getProperty("passLabelWidth")),
                    Integer.parseInt(properties.getProperty("passLabelHeight")));
            nameLabel.setText(properties.getProperty("nameLabel"));
            nameLabel.setBounds(Integer.parseInt(properties.getProperty("nameLabelX")),
                    Integer.parseInt(properties.getProperty("nameLabelY")),
                    Integer.parseInt(properties.getProperty("nameLabelWidth")),
                    Integer.parseInt(properties.getProperty("nameLabelHeight")));
            emailLabel.setText(properties.getProperty("emailLabel"));
            emailLabel.setBounds(Integer.parseInt(properties.getProperty("emailLabelX")),
                    Integer.parseInt(properties.getProperty("emailLabelY")),
                    Integer.parseInt(properties.getProperty("emailLabelWidth")),
                    Integer.parseInt(properties.getProperty("emailLabelHeight")));
            phoneLabel.setText(properties.getProperty("phoneLabel"));
            phoneLabel.setBounds(Integer.parseInt(properties.getProperty("phoneLabelX")),
                    Integer.parseInt(properties.getProperty("phoneLabelY")),
                    Integer.parseInt(properties.getProperty("phoneLabelWidth")),
                    Integer.parseInt(properties.getProperty("phoneLabelHeight")));
            userTextField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            userTextField.setBounds(Integer.parseInt(properties.getProperty("userFieldX")),
                    Integer.parseInt(properties.getProperty("userFieldY")),
                    Integer.parseInt(properties.getProperty("userFieldWidth")),
                    Integer.parseInt(properties.getProperty("userFieldHeight")));
            passwordField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            passwordField.setBounds(Integer.parseInt(properties.getProperty("passFieldX")),
                    Integer.parseInt(properties.getProperty("passFieldY")),
                    Integer.parseInt(properties.getProperty("passFieldWidth")),
                    Integer.parseInt(properties.getProperty("passFieldHeight")));
            nameField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            nameField.setBounds(Integer.parseInt(properties.getProperty("nameFieldX")),
                    Integer.parseInt(properties.getProperty("nameFieldY")),
                    Integer.parseInt(properties.getProperty("nameFieldWidth")),
                    Integer.parseInt(properties.getProperty("nameFieldHeight")));
            emailField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            emailField.setBounds(Integer.parseInt(properties.getProperty("emailFieldX")),
                    Integer.parseInt(properties.getProperty("emailFieldY")),
                    Integer.parseInt(properties.getProperty("emailFieldWidth")),
                    Integer.parseInt(properties.getProperty("emailFieldHeight")));
            phoneField.setBackground(new Color(Integer.parseInt(properties.getProperty("red")),
                    Integer.parseInt(properties.getProperty("green")),
                    Integer.parseInt(properties.getProperty("blue"))));
            phoneField.setBounds(Integer.parseInt(properties.getProperty("phoneFieldX")),
                    Integer.parseInt(properties.getProperty("phoneFieldY")),
                    Integer.parseInt(properties.getProperty("phoneFieldWidth")),
                    Integer.parseInt(properties.getProperty("phoneFieldHeight")));
            registerButton.setText(properties.getProperty("register"));
            registerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            registerButton.setBounds(Integer.parseInt(properties.getProperty("registerX")),
                    Integer.parseInt(properties.getProperty("registerY")),
                    Integer.parseInt(properties.getProperty("registerWidth")),
                    Integer.parseInt(properties.getProperty("registerHeight")));
            showPassword.setText(properties.getProperty("showPassword"));
            showPassword.setBounds(Integer.parseInt(properties.getProperty("showPassX")),
                    Integer.parseInt(properties.getProperty("showPassY")),
                    Integer.parseInt(properties.getProperty("showPassWidth")),
                    Integer.parseInt(properties.getProperty("showPassHeight")));
            warnLabel.setBounds(Integer.parseInt(properties.getProperty("warnX")),
                    Integer.parseInt(properties.getProperty("warnY")),
                    Integer.parseInt(properties.getProperty("warnWidth")),
                    Integer.parseInt(properties.getProperty("warnHeight")));
        }catch (Exception e){
            e.printStackTrace();
        }
        //JLabel userLabel = new JLabel("USERNAME");
        //userLabel.setBounds(50, 150, 100, 30);
        //JLabel passwordLabel = new JLabel("PASSWORD");
        //passwordLabel.setBounds(50, 220, 100, 30);
        //JLabel nameLabel = new JLabel("FULL NAME");
        //nameLabel.setBounds(50,310,100,30);
        //JLabel emailLabel = new JLabel("EMAIL");
        //emailLabel.setBounds(50,370,100,30);
       // JLabel phoneLabel = new JLabel("PHONE NUMBER");
       // phoneLabel.setBounds(50,440,100,30);
        //JTextField userTextField = new JTextField();
        //userTextField.setBackground(new Color(255,204,51));
        //userTextField.setBounds(170, 150, 200, 30);
        //JPasswordField passwordField = new JPasswordField();
        //passwordField.setBackground(new Color(255,204,51));
       // passwordField.setBounds(170, 220, 200, 30);
       // JTextField nameField = new JTextField();
        //nameField.setBackground(new Color(255,204,51));
        //nameField.setBounds(170,310,200,30);
        //JTextField emailField = new JTextField();
       // emailField.setBackground(new Color(255,204,51));
       // emailField.setBounds(170,370,200,30);
        //JTextField phoneField = new JTextField();
       // phoneField.setBackground(new Color(255,204,51));
       // phoneField.setBounds(170,440,200,30);
        //JButton registerButton = new JButton("REGISTER");
       // registerButton.setBackground(new Color(51,153,200));
       // registerButton.setBounds(200, 510, 100, 30);
        //JCheckBox showPassword = new JCheckBox("Show Password");
        //showPassword.setBounds(170, 250, 150, 30);
        //JLabel warnLabel = new JLabel();
        //warnLabel.setBounds(50,100,200,20);
        closeButton();
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(userLabel);
        MainController.panel.add(passwordLabel);
        MainController.panel.add(userTextField);
        MainController.panel.add(passwordField);
        MainController.panel.add(nameLabel);
        MainController.panel.add(nameField);
        MainController.panel.add(emailLabel);
        MainController.panel.add(emailField);
        MainController.panel.add(phoneLabel);
        MainController.panel.add(phoneField);
        MainController.panel.add(registerButton);

        MainController.panel.add(showPassword);
        MainController.panel.add(warnLabel);
        MainController.panel.revalidate();
        MainController.panel.repaint();

        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }

            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText;
                String pwdText;
                String nameText;
                String emailText;
                String phoneText;
                userText = userTextField.getText();
                pwdText = String.valueOf(passwordField.getPassword());
                nameText = nameField.getText();
                emailText = emailField.getText();
                phoneText =phoneField.getText();
                RegisterAgent register = new RegisterAgent();
                if (register.validateRegister(nameText,userText,pwdText,emailText,phoneText)){
                    try {
                        FileReader reader = new FileReader("resources/configuration/register.properties");
                        Properties properties = new Properties();
                        properties.load(reader);
                        warnLabel.setText(properties.getProperty("warn1"));
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                    //warnLabel.setText("registration successful");
                    MainController.username = userText;
                    MenuView menuView = new MenuView();
                    menuView.menuPage();
                }
                else
                    try {
                        FileReader reader = new FileReader("resources/configuration/register.properties");
                        Properties properties = new Properties();
                        properties.load(reader);
                        warnLabel.setText(properties.getProperty("warn2"));
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }

                    //warnLabel.setText("invalid! try again");

            }
        });



    }


}
