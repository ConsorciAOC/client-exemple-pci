package cat.aoc.client_pci.clients.vo.registre_entitats;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.registre_entitats.RespostaInscripcionsEntitats;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class REClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private REClient client;
    @BeforeEach
    void setUp() {
        client = new REClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (REOperacio operacio : REOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new REPeticionBuilder(PROPERTIES_PATH).build(REOperacio.ENTITAT_INSCRIPCIO, Finalitat.PROVES);
        Respuesta respuesta = client.send(REOperacio.ENTITAT_INSCRIPCIO, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaInscripcionsEntitats dadesCompletes = (RespostaInscripcionsEntitats) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(dadesCompletes);
    }
}