package logic;

import model.Group;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class CategoryAgent {
    private static final Logger logger = LogManager.getLogger(CategoryAgent.class);

    public void addCategory(String username, String name){
        User user = Load.loadUser(username);
        Group group = new Group(name);
        user.getGroups().add(group);
        logger.info(user.getUsername() + " " + "added a category");
        user.updateLastSeen();
        user.saveUser();
    }
    public LinkedList<Group> showGroups(String username){
        User user = Load.loadUser(username);
        return user.getGroups();
    }
    public void deleteGroup(String username, Group grpName){
        User user = Load.loadUser(username);
        user.getGroups().remove(grpName);
        logger.info(user.getUsername() + " " + "deleted a group");
        user.updateLastSeen();
        user.saveUser();
    }
    public void addToGroup (String you, String member, Group grpname){
        User member_user = Load.loadUser(member);
        User user_you = Load.loadUser(you);
        if (member_user == null)
            return;
        for (Group group : user_you.getGroups())
            if (group.getName().equals(grpname.getName()))
                group.getUsernames().add(member);
        user_you.saveUser();
    }
    public LinkedList<String> showMembers(String username, Group group){
        LinkedList<String> usernames = new LinkedList<>();
        User user = Load.loadUser(username);
        for (String target : group.getUsernames()){
            usernames.add(target);
        }
        return usernames;
    }
    public void deleteName(String username, String member, Group group){
        User user = Load.loadUser(username);
        User member_user = Load.loadUser(member);
        group.getUsernames().remove(member_user.getUsername());
        user.updateLastSeen();
        user.saveUser();
    }
}
