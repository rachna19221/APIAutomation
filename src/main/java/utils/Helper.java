package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Helper {
    public static String commonFilePath = System.getProperty("user.dir") + "/config.properties";

    public static String propertyReader(String path, String key) {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            prop.load(fis);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createFolder(String folderPath){
        File folder = new File(folderPath);

        // Check if the folder already exists
        if (!folder.exists()) {
            boolean success = folder.mkdirs(); // Creates folder + parent folders if needed
            if (success) {
                System.out.println("Folder created successfully at: " + folderPath);
            } else {
                System.out.println("Failed to create the folder.");
            }
        } else {
            System.out.println("Folder already exists at: " + folderPath);
        }

    }

    public static String getTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return formatter.format(new Date());
    }
}
