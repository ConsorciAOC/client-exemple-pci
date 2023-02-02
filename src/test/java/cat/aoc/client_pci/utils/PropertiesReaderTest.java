package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;

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
                NotFoundException.class,
                () -> PropertiesReader.load("iDontExist.properties")
        );
    }

}
