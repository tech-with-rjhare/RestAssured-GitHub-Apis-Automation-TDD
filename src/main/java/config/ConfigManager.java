package config;

import io.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    protected static final Properties properties = new Properties();
    private static final String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file. Check path: " + filePath, e);
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

    public static String getBaseURI(){
        return properties.getProperty("base_url");
    }

    public static void setBaseURI(){
        RestAssured.baseURI = properties.getProperty("base_url");
    }

}
