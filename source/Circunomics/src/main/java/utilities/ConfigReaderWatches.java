package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReaderWatches {
    private static Properties properties;

    static {
        try {
            // Load the properties file
            String filePath = "./src/test/resources/testdatawatches.properties";
            FileInputStream input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get a property value by key
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in testdata.properties.");
        }
        return value;
    }
}