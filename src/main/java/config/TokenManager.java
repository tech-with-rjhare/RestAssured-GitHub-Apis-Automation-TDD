package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static config.ConfigManager.properties;

public class TokenManager{

    private static final String tokenFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\token.properties";

    static {
        try {
            FileInputStream tokenFileInputStream = new FileInputStream(tokenFilePath);
            properties.load(tokenFileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load token.properties file. Check path: " + tokenFilePath, e);
        }
    }
    public static String getToken(){
        return ConfigManager.getValue("GITHUB_TOKEN");
    }
}
