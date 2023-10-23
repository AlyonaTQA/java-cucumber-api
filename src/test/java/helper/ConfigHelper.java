package helper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {
    private Properties properties;
    public void init() {
        properties = new Properties();

        try (FileInputStream input = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(input);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
