package framework;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try (InputStream input =
             ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        try {
            return System.getProperty(key, properties.getProperty(key));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read config key: " + key, e);
        }
    }
}
