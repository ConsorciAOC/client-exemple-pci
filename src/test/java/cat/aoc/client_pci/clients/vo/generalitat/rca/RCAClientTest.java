package cat.aoc.client_pci.clients.vo.generalitat.rca;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.rca.RespostaVerificacioAssegurat;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RCAClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private RCAClient client;

    @BeforeEach
    void setUp() {
        client = new RCAClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (RCAOperacio operacio : RCAOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new RCAPeticionBuilder(PROPERTIES_PATH).build(RCAOperacio.RCA_VERIFICACIO, Finalitat.PROVES);
        Respuesta respuesta = client.send(RCAOperacio.RCA_VERIFICACIO, peticion);
        RespostaVerificacioAssegurat resposta = (RespostaVerificacioAssegurat) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }
}