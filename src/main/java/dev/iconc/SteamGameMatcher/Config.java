package dev.iconc.SteamGameMatcher;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final String configFileName = "apiKey.properties";

    private static Properties loadPropertiesFile(String filename, Properties properties) {
        File file = new File(filename);

        CreatePropertiesFile(file, properties);

        try {
            properties.load(new FileInputStream(file));

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

        return properties;
    }

    private static boolean CreatePropertiesFile(File file, Properties properties) {
        try {
            if (!file.createNewFile()) return false;

            properties.store(new FileOutputStream(file),"");

        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
        return true;
    }



    public static String getApiKey() {
        Properties properties = new Properties();
        properties.setProperty("apiKey", "");

        properties = loadPropertiesFile("./" + configFileName, properties);

        if (properties == null) {
            return null;
        }

        if (properties.getProperty("apiKey").isEmpty()) {
            System.out.println("The API Key seems to be empty, plaese supply an API Key in " + configFileName);
            System.exit(0);
        }

        return properties.getProperty("apiKey");
    }

    public static void main(String[] args) {
        System.out.println("API Key: " + getApiKey());
    }
}
