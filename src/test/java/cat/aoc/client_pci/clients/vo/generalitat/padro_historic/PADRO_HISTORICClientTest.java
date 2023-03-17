package cat.aoc.client_pci.clients.vo.generalitat.padro_historic;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.padro_historic.RespuestaDatosConvivientesHistorico;
import generated.padro_historic.RespuestaDatosTitularHistorico;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PADRO_HISTORICClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private PADRO_HISTORICClient client;

    @BeforeEach
    void setUp() {
        client = new PADRO_HISTORICClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (PADRO_HISTORICOperacio operacio : PADRO_HISTORICOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void titular() throws ClientException {
        Peticion peticion = new PADRO_HISTORICPeticionBuilder(PROPERTIES_PATH).build(PADRO_HISTORICOperacio.TITULAR_HISTORIC, Finalitat.PROVES);
        Respuesta respuesta = client.send(PADRO_HISTORICOperacio.TITULAR_HISTORIC, peticion);
        RespuestaDatosTitularHistorico resposta = (RespuestaDatosTitularHistorico) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
        assertNotNull(resposta.getPeriodo());
    }

    @Test
    void convivents() throws ClientException {
        Peticion peticion = new PADRO_HISTORICPeticionBuilder(PROPERTIES_PATH).build(PADRO_HISTORICOperacio.CONVIVENTS_HISTORIC, Finalitat.PROVES);
        Respuesta respuesta = client.send(PADRO_HISTORICOperacio.CONVIVENTS_HISTORIC, peticion);
        RespuestaDatosConvivientesHistorico resposta = (RespuestaDatosConvivientesHistorico) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
        assertNotNull(resposta.getPeriodo());
    }

}
