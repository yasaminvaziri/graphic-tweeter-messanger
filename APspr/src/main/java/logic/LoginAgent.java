package logic;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginAgent {
    private static final Logger logger = LogManager.getLogger(LoginAgent.class);
    public boolean validateLogin(String username, String password) {
        if (RegisterAgent.validUsername(username) && RegisterAgent.isValidPassword(password)) {

            User user1 = Load.loadUser(username);
            if (user1 != null) {

                if (user1.getUsername().equals(username) &&
                        user1.getPassword().equals(password)) {
                    user1.setActive(true);
                    user1.setOnline(true);
                    logger.info(user1.getUsername() + " " + "logged in");
                    user1.updateLastSeen();
                    return true;
                }
            }else
                return false;

        }
        return false;
    }
}
