package logic;


import model.Tweet;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.LinkedList;

public class TweetAgent {
    private static final Logger logger = LogManager.getLogger(TweetAgent.class);

    public void addTweet(String username,String t, File f){
        User user = Load.loadUser(username);
        Tweet tweet = new Tweet(user,t);
        user.getTweetList().add(tweet.getId());
        user.updateLastSeen();
        if (f != null)
            FileImage.saveTweetPhoto(f, tweet.getId());
        tweet.saveTweet();
        user.saveUser();
          logger.info(user.getUsername() + " added a tweet");
    }

    public LinkedList<Tweet> showTweet(String username) {
        LinkedList<Integer> followingTweets = new LinkedList<>();
        User user = Load.loadUser(username);
        followingTweets.addAll(user.getTweetList());
        for (String target: user.getFollowings()) {
            User targetUser = Load.loadUser(target);
            if (targetUser != null && !user.getMuted().contains(targetUser.getUsername())) {
                followingTweets.addAll(targetUser.getTweetList());
            }
        }return idToTweet(followingTweets);
    }


    public LinkedList<Tweet> idToTweet(LinkedList<Integer> ar) {
        LinkedList<Tweet> res= new LinkedList<>();
        for (int i : ar)
            res.add(Load.loadTweet(i));
        return res;
    }
    public void addComment(String username,String comment,Tweet tweet){
        User user = Load.loadUser(username);
        Tweet new_tweet = new Tweet(user,comment);
        tweet.getCommentList().add(new_tweet.getId());
        logger.info(user.getUsername() + " " + "added a comment");
        new_tweet.saveTweet();
        user.updateLastSeen();
        user.saveUser();
        tweet.saveTweet();
    }

    public boolean findUser(String username, String target){
        User user = Load.loadUser(username);
        User targetUser = Load.loadUser(target);
        if (targetUser == null){
            return false;
        }
        else if(user == null || user.getReportedU().contains(targetUser.getUsername())){
            return false;
        }
        return true;
    }

    public void likeTweet(Tweet tweet, String username){
        User user = Load.loadUser(username);
        if (!tweet.getLiker().contains(user.getUsername())) {
            tweet.like++;
            user.updateLastSeen();
            tweet.getLiker().add(user.getUsername());
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "liked tweet");
            tweet.getUser().saveUser();
        }
        else
            return;
    }

    public void dislikeTweet(Tweet tweet, String username){
        User user = Load.loadUser(username);
        if (tweet.getLiker().contains(user.getUsername())){
            tweet.like--;
            user.updateLastSeen();
            tweet.getLiker().remove(username);
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "disliked a tweet");
            tweet.getUser().saveUser();
        }
        else
            return;
    }
    public void reportTweet(Tweet tweet, String username){
        User user = Load.loadUser(username);
        if (!tweet.getReportedBy().contains(user.getUsername())){
            tweet.getReportedBy().add(user.getUsername());
            tweet.saveTweet();
            logger.info(user.getUsername() + " " + "reported a tweet");
            tweet.getUser().saveUser();
            if (tweet.getReportedBy().size() >= 3) {
                tweet.getUser().getTweetList().remove(tweet);
                deleteTweet(tweet);
            }else
                return;
        }
        else
            return;
    }

    public void deleteTweet(Tweet tweet) {
        if (tweet != null) {
            try {
                String path = "resources/saveTweet";
                path += "/" + tweet.getId();
                path += ".txt";
                File file = new File(path);
                if (file.exists()) {
                    System.out.println("1");
                }
                if (file.delete()) {
                    System.out.println(file.getName() + "deleted");
                    logger.info(tweet.getT() + " deleted file ");
                    System.exit(0);

                } else {
                    System.out.println("failed");
                     logger.error(" could not delete file");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("tweet does not exist");
        }
    }

    public Tweet retweet(Tweet tweet, String username){
        User user = Load.loadUser(username);
        Tweet retweeted_tweet = new Tweet(user,tweet.getT());
        user.getTweetList().add(retweeted_tweet.getId());
        retweeted_tweet.setrTweet(tweet);
        retweeted_tweet.saveTweet();
        user.updateLastSeen();
        user.saveUser();
        logger.info(user.getUsername() + " " + "retweeted a tweet");
        return tweet;
    }

    public LinkedList<Tweet> discover(String username){
        LinkedList<Integer> allTweets = new LinkedList<>();
        User user = Load.loadUser(username);
        for (int i = 0; i < Load.tweetList.size(); i++) {
            if (user.getMuted().contains(Load.tweetList.get(i).getUser().getUsername()) ||
                    user.getBlockList().contains(Load.tweetList.get(i).getUser().getUsername()) ||
                    user.getReportedU().contains(Load.tweetList.get(i).getUser().getUsername())
                    || !Load.tweetList.get(i).getUser().isPublic())
                continue;
            allTweets.add(Load.tweetList.get(i).getId());
        }
        return idToTweet(allTweets);
    }

}
