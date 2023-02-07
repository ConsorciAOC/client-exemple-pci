package cat.aoc.client_pci.clients.over;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.exceptions.NotFoundException;
import generated.over.RespostaDocumentacioTramit;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OVERClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private OVERClient client;
    @BeforeEach
    void setUp() {
        client = new OVERClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws NotDefinedException {
        for (OVEROperacio operacio : OVEROperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void getCodiServei() {
        assertEquals("OVER", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() {
        for (OVEROperacio operacio : OVEROperacio.values()) {
            assertEquals(operacio.name(), client.getCodiModalitat(operacio));
        }
    }

    @Test
    void send() throws NotDefinedException, NotFoundException {
        Peticion peticion = new OVERPeticionBuilder(PROPERTIES_PATH).build(OVEROperacio.OVER_DOCUMENTACIO, Finalitat.PROVES);
        Respuesta respuesta = client.send(OVEROperacio.OVER_DOCUMENTACIO, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaDocumentacioTramit resposta = (RespostaDocumentacioTramit) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}
