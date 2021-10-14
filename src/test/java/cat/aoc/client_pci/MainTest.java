package cat.aoc.client_pci;

import cat.aoc.client_pci.jaxb.pci.Atributos;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final String ENOTUM = "ENOTUM";

    @Test
    void testJaxb(){
        Atributos atributos = new Atributos();
        atributos.setCodigoProducto(ENOTUM);
        assertEquals(ENOTUM, atributos.getCodigoProducto());
    }

}