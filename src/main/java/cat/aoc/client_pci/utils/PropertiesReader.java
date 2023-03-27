package cat.aoc.client_pci.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface PropertiesReader {

    static Properties load(String propertiesPath) throws IOException {
        try (InputStream input = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }

}
