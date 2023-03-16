package cat.aoc.client_pci.clients.rpe;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.rpe.RespostaConsultaParellesEstables;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RPEClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private RPEClient client;
    @BeforeEach
    void setUp() {
        client = new RPEClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (RPEOperacio operacio : RPEOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new RPEPeticionBuilder(PROPERTIES_PATH).build(RPEOperacio.RPE_CONSULTA, Finalitat.PROVES);
        Respuesta respuesta = client.send(RPEOperacio.RPE_CONSULTA, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaConsultaParellesEstables respostaConsultaPrestacions = (RespostaConsultaParellesEstables) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaConsultaPrestacions);
    }

}
