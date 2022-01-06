package graphic.welcome;

import graphic.interfaces.CloseButton;
import logic.LoginAgent;
import util.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.Properties;

public class LoginView implements CloseButton {




    public void loginPage(){
        MainController.panel.removeAll();
        JLabel userLabel = new JLabel();
        JLabel passwordLabel = new JLabel();
        JTextField userTextField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton();
        JButton registerButton = new JButton();
        JCheckBox showPassword = new JCheckBox();
        JLabel warnLabel = new JLabel();
        try {
            FileReader reader = new FileReader("resources/configuration/login.properties");
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
            loginButton.setText(properties.getProperty("login"));
            loginButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            loginButton.setBounds(Integer.parseInt(properties.getProperty("loginX")),
                    Integer.parseInt(properties.getProperty("loginY")),
                    Integer.parseInt(properties.getProperty("loginWidth")),
                    Integer.parseInt(properties.getProperty("loginHeight")));
            registerButton.setText(properties.getProperty("register"));
            registerButton.setBackground(new Color(Integer.parseInt(properties.getProperty("r")),
                    Integer.parseInt(properties.getProperty("g")),
                    Integer.parseInt(properties.getProperty("b"))));
            registerButton.setBounds(Integer.parseInt(properties.getProperty("registerX")),
                    Integer.parseInt(properties.getProperty("registerY")),
                    Integer.parseInt(properties.getProperty("registerWidth")),
                    Integer.parseInt(properties.getProperty("registerHeight")));
            showPassword.setText(properties.getProperty("showPass"));
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
        ///JLabel passwordLabel = new JLabel("PASSWORD");
        //passwordLabel.setBounds(50, 220, 100, 30);
        //JTextField userTextField = new JTextField();
        //userTextField.setBackground(new Color(255,204,51));
        //userTextField.setBounds(150, 150, 150, 30);
        //JPasswordField passwordField = new JPasswordField();
        //passwordField.setBackground(new Color(255,204,51));
        //passwordField.setBounds(150, 220, 150, 30);
        //JButton loginButton = new JButton("LOGIN");
        //loginButton.setBackground(new Color(51,153,200));
        //loginButton.setBounds(50, 300, 100, 30);
        //JButton registerButton = new JButton("REGISTER");
        //registerButton.setBackground(new Color(51,153,200));
       // registerButton.setBounds(200, 300, 100, 30);
        //JCheckBox showPassword = new JCheckBox("Show Password");
        //showPassword.setBounds(150, 250, 150, 30);
        //JLabel warnLabel = new JLabel();
        //warnLabel.setBounds(50,100,200,10);
        closeButton();
        MainController.panel.setBackground(Color.lightGray);
        MainController.panel.add(userLabel);
        MainController.panel.add(passwordLabel);
        MainController.panel.add(userTextField);
        MainController.panel.add(passwordField);
        MainController.panel.add(loginButton);
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

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText;
                String pwdText;
                userText = userTextField.getText();
                pwdText = String.valueOf(passwordField.getPassword());
               LoginAgent l = new LoginAgent();
               if (l.validateLogin(userText,pwdText)){
                   try {
                       FileReader reader = new FileReader("resources/configuration/login.properties");
                       Properties properties = new Properties();
                       properties.load(reader);
                       warnLabel.setText(properties.getProperty("warn1"));
                   }catch (Exception exception){
                       exception.printStackTrace();
                   }
                   //warnLabel.setText("Login Successful");
                   MainController.username = userText;
                   MenuView menuView = new MenuView();
                   menuView.menuPage();

                }

               else
                   try {
                       FileReader reader = new FileReader("resources/configuration/login.properties");
                       Properties properties = new Properties();
                       properties.load(reader);
                       warnLabel.setText(properties.getProperty("warn2"));
                   }catch (Exception exception){
                       exception.printStackTrace();
                   }
                   // warnLabel.setText("Invalid Username or Password");


            }

        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterView registerView = new RegisterView();
                registerView.registerPage();

            }
        });

    }




}

