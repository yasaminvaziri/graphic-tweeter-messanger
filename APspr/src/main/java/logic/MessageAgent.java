package logic;

import model.Tweet;
import model.User;
import model.messaging.Chat;
import model.messaging.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.LinkedList;

public class MessageAgent {
    private static final Logger logger = LogManager.getLogger(MessageAgent.class);

    public void addMessageToSaved(String sender, String text){
        User user = Load.loadUser(sender);
        Message message = new Message(sender,text);
        message.setSeen(true);
        user.getSavedMessages().add(message);
        logger.info(user.getUsername() + "added a message for herself");
        user.updateLastSeen();
        user.saveUser();
    }
    public void addTweetToSaved(String sender, Tweet tweet){
        User user = Load.loadUser(sender);
        Message message = new Message(sender, tweet);
        message.setSeen(true);
        user.getSavedMessages().add(message);
        logger.info(user.getUsername() + " " + "saved a tweet");
        user.updateLastSeen();
        user.saveUser();
    }
    public LinkedList<Message> showSavedMessages(String username){
        LinkedList<Message> messages = new LinkedList<>();
        User user = Load.loadUser(username);
        for (Message target : user.getSavedMessages()){
            messages.add(target);
        }
        return messages;
    }
    public void deleteSavedMessage(String username, Message message){
        User user = Load.loadUser(username);
        user.getSavedMessages().remove(message);
        logger.info(user.getUsername() + " " + "deleted a message");
        user.updateLastSeen();
        user.saveUser();
    }
    public void editSavedMessage(Message message, String text){
        User user = Load.loadUser(message.getSender());
        message.setMessage(text);
        logger.info(user.getUsername() + " " + "edited a message");
        user.updateLastSeen();
        user.saveUser();
    }
    public String addNameToChats(String you, String target) {
        User user_you = Load.loadUser(you);
        User target_user = Load.loadUser(target);
        if (target_user != null && target_user.getFollowers().contains(user_you.getUsername())
                && user_you.getFollowers().contains(target_user.getUsername())){
            Chat chat = new Chat(user_you.getUsername(), target_user.getUsername());
            user_you.getChats().add(chat);
            target_user.getChats().add(chat);
            user_you.updateLastSeen();
            user_you.saveUser();
            target_user.saveUser();
            return target;
        }
        else
            return null;
    }
    public LinkedList<Chat> showNames(String username){

        return Load.loadUser(username).getChats();
    }

    public LinkedList<Message> showMessagesInChat(String username, String targetUser){
        User user = Load.loadUser(username);
        for (Chat chat : user.getChats()){
            if (targetUser.equals(chat.user2) || targetUser.equals(chat.user1))
                return chat.getMessages();
        }
        return new LinkedList<>();
    }

    public void addMessage(String username, String text, String target, File f) {
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())){
                Message message = new Message(user.getUsername(),text);
                chat.getMessages().add(message);
                if (f != null)
                    FileImage.saveMessagePhoto(f,message.getId());
                message.saveMessage();
                logger.info(user.getUsername() + " " + "added a message");
                user.saveUser();
                break;
            }
        }
        for (Chat chat : targetUser.getChats()) {
            if (chat.user1.equals(targetUser.getUsername()) || chat.user2.equals(targetUser.getUsername())) {
                Message message = new Message(user.getUsername(), text);
                chat.getMessages().add(message);
                if (f != null)
                    FileImage.saveMessagePhoto(f,message.getId());
                logger.info(user.getUsername() + " " + "added a message");
                message.saveMessage();
                targetUser.saveUser();
            }
        }
    }

    public void deleteMessage(String username, String target, Message message){
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())) {
                for (int i = 0; i < chat.getMessages().size(); i++) {
                    chat.getMessages().remove(message);
                    user.saveUser();
                    break;
                }
            }
        }

        for (Chat chat : targetUser.getChats()){
            if (chat.user1.equals(user.getUsername()) || chat.user2.equals(user.getUsername())) {
                for (int i = 0; i < chat.getMessages().size(); i++) {
                    chat.getMessages().remove(message);
                    targetUser.saveUser();

                }
            }
        }

    }
    public void editMessage(String username,String target, Message message, String text) {
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        for (Chat chat : user.getChats()) {
            for (int i = 0; i < chat.getMessages().size(); i++) {
                message.setMessage(text);
                user.saveUser();
            }
        }
        for (Chat chat : targetUser.getChats()) {
            for (int i = 0; i < chat.getMessages().size(); i++) {
                message.setMessage(text);
                targetUser.saveUser();
            }
        }
    }

    public void forwardTweet(String username, String target, Tweet tweet){
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        if (targetUser != null) {
            if (user.getFollowings().contains(targetUser.getUsername())) {
                for (Chat chat : user.getChats()) {
                    if (chat.user2.equals(targetUser.getUsername()) || chat.user1.equals(targetUser.getUsername())) {
                        Message message = new Message(user.getUsername(), tweet);
                        chat.getMessages().add(message);
                        message.saveMessage();
                        user.saveUser();
                        break;
                    }
                }
                for (Chat chat : targetUser.getChats()) {
                    if (chat.user2.equals(user.getUsername()) || chat.user1.equals(user.getUsername())) {
                        Message message = new Message(username, tweet);
                        message.saveMessage();
                        chat.getMessages().add(message);
                        targetUser.saveUser();
                        return;

                    }
                }

                Chat chat = new Chat(user.getUsername(), targetUser.getUsername());
                user.getChats().add(chat);
                targetUser.getChats().add(chat);
                Message message = new Message(user.getUsername(), tweet);
                user.getChats().get(user.getChats().size() - 1).getMessages().add(message);
                targetUser.getChats().get(targetUser.getChats().size() - 1).getMessages().add(message);
                user.saveUser();
                targetUser.saveUser();
            }
        }else
            return;
    }
}
