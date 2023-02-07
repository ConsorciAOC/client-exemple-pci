package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.model.exceptions.ClientException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface PropertiesReader {

    static Properties load(String propertiesPath) throws ClientException {
        try (InputStream input = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (IOException ex) {
            throw new ClientException("Fitxer .properties no trobat: " + propertiesPath);
        }
    }

}
