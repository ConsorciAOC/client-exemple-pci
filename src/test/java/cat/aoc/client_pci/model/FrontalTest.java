package cat.aoc.client_pci.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrontalTest {

    @Test
    void getName() {
        assertEquals("Sincron", Frontal.SINCRON.getName());
        assertEquals("Asincron", Frontal.ASINCRON.getName());
        assertEquals("AsincronResposta", Frontal.ASINCRON_RESPOSTA.getName());
    }

}