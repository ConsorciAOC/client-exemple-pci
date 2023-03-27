package cat.aoc.client_pci.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesReaderTest {

    @Test
    void foundProperties() {
        assertDoesNotThrow(
                () -> {
                    Properties properties = PropertiesReader.load("src\\test\\resources\\test.properties");
                    assertEquals("world", properties.getProperty("hello"));
                }
        );
    }

    @Test
    void notFoundProperties() {
        assertThrows(
                IOException.class,
                () -> PropertiesReader.load("iDontExist.properties")
        );
    }

}
