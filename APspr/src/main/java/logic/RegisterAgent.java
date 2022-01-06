package logic;


import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterAgent {
    private static final Logger logger = LogManager.getLogger(RegisterAgent.class);



    public static boolean validName(String fullName) {
        int charCount = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        if (fullName.length() == 0) {
            return false;
        }
        for (int i = 0; i < fullName.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (fullName.substring(i, i + 1).equals(alphabet.substring(j, j + 1))
                        || fullName.substring(i, i + 1).equals(alphabet.substring(j, j + 1).toLowerCase())) {
                    charCount++;
                }
            }
            if (charCount != (i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validUsername(String username) {
        int charCount = 0;
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        if (username.length() == 0) {
            return false;
        }
        for (int i = 0; i < username.length(); i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                if (username.substring(i, i + 1).equals(alphabet.substring(j, j + 1))
                        || username.substring(i, i + 1).equals(alphabet.substring(j, j + 1).toLowerCase())) {
                    charCount++;
                }
            }
            if (charCount != (i + 1)) {
                return false;
            }
        }
        return true;
    }


    public static final int PASSWORD_LENGTH = 8;

    public static boolean isValidPassword(String password) {
        if (password.length() < PASSWORD_LENGTH) return false;
        int charCount = 0;
        int numCount = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (is_Numeric(ch)) numCount++;
            else if (is_Letter(ch)) charCount++;
            else return false;
        }
        return (charCount >= 2 && numCount >= 2);
    }

    public static boolean is_Letter(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'Z');
    }

    public static boolean is_Numeric(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public static final int EMAIL_LENGTH = 16;

    public static boolean isValidEmail(String email) {
        if (email.length() < EMAIL_LENGTH) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return true;
    }

    public static final int PHONE_LENGTH = 13;

    public static boolean isValidPhoneNumber(String phoneNumber) {
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!(i >= '0') && !(i <= '9')) {
                return false;
            } else if (phoneNumber.length() < PHONE_LENGTH || phoneNumber.charAt(0) != '+') {
                return false;
            }
        }
        return true;
    }

    public boolean validateRegister(String fullName, String username, String password, String email, String phoneNumber) {
        if (RegisterAgent.validName(fullName) && RegisterAgent.isValidPassword(password) && RegisterAgent.isValidEmail(email) && RegisterAgent.isValidPhoneNumber(phoneNumber)){
            User user1 = Load.loadUser(username);
            if (user1 != null){
                return false;
            }
            else if (user1 == null){
                User user = new User(username,email,password,fullName,null,phoneNumber,null);
                SaveEmail.saveEmail(username);
                SavePhone.savePhone(username);
                logger.info(user.getUsername() + " " + "registered");
                user.setActive(true);
                user.setOnline(true);
                user.updateLastSeen();
                return true;

            }
        }else
            return false;
        return false;
    }


}









