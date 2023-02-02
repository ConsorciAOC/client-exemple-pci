package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ENOTUMClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private ENOTUMClient client;
    @BeforeEach
    void setUp() {
        client = new ENOTUMClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws NotDefinedException {
        for (ENOTUMOperacio operacio : ENOTUMOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void getCodiServei() {
        assertEquals("ENOTUM", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() {
        for (ENOTUMOperacio operacio : ENOTUMOperacio.values()) {
            assertEquals("ENOTUM", client.getCodiModalitat(operacio));
        }
    }

    @Test
    void sendProcessarTramesa() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ENOTUMPeticionBuilder(PROPERTIES_PATH).build("ENOTUM", ENOTUMOperacio.PROCESSAR_TRAMESA, Finalitat.PROVES);
        Respuesta respuesta = client.send(ENOTUMOperacio.PROCESSAR_TRAMESA, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaProcessarTramesa processarTramesa = (RespostaProcessarTramesa) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getNotificacionsCreades().getIdNotificacio().get(0));
    }

}