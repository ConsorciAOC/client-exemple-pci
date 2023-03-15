package cat.aoc.client_pci.clients.dgt;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.dgp.RespostaConsultaDadesIdentitat;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DGTClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private DGTClient client;

    @BeforeEach
    void setUp() {
        client = new DGTClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (DGTOperacio operacio : DGTOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new DGTPeticionBuilder(PROPERTIES_PATH).build(DGTOperacio.DGT_DADES_VEHICLE, Finalitat.PROVES);
        Respuesta respuesta = client.send(DGTOperacio.DGT_DADES_VEHICLE, peticion);
        RespostaConsultaDadesIdentitat resposta = (RespostaConsultaDadesIdentitat) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
        assertNotNull(resposta.getDadesTitular());
    }

}