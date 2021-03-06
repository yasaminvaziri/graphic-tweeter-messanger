package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


public class SaveEmail {
    private static final Logger logger = LogManager.getLogger(SaveEmail.class);
    public static void saveEmail(String username){
        User user =  Load.loadUser(username);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().serializeNulls().create();
        String s = gson.toJson(user.getEmail());
        String path = "resources/saveEmail";
        path +="/" + (user.getId());
        path += ".txt";
        File file = new File(path);
        try {
            if (!file.exists())
                file.createNewFile();
            else {
                file.delete();
                file.createNewFile();
                logger.info("email-file created for user");
            }
            PrintStream printStream = new PrintStream(new FileOutputStream(file));
            gson.toJson(user.getEmail(), printStream);
            printStream.close();
        }catch (IOException e){
            System.out.println("error");
            logger.info("could not create file for user");
        }

    }
}
