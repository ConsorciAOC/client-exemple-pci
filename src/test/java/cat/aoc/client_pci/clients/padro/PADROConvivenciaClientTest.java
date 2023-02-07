package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PADROConvivenciaClientTest {

    private static final String PROPERTIES_PATH = "src\\test\\resources\\test.properties";

    private PADROConvivenciaClient client;
    @BeforeEach
    void setUp() {
        client = new PADROConvivenciaClient(PROPERTIES_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws NotDefinedException {
        for (PADROOperacio operacio : PADROOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void getCodiServei() {
        assertEquals("PADRO", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() {
        for (PADROOperacio operacio : PADROOperacio.values()) {
            assertEquals(operacio.name(), client.getCodiModalitat(operacio));
        }
    }

}