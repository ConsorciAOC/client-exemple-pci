package cat.aoc.client_pci.clients.vo.estat.estrangeria;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.estrangeria.RespostaConsultaDadesResidenciaLegal;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ESTRANGERIAClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private ESTRANGERIAClient client;

    @BeforeEach
    void setUp() {
        client = new ESTRANGERIAClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (ESTRANGERIAOperacio operacio : ESTRANGERIAOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new ESTRANGERIAPeticionBuilder(PROPERTIES_PATH).build(ESTRANGERIAOperacio.RESIDENCIA_LEGAL, Finalitat.PROVES);
        Respuesta respuesta = client.send(ESTRANGERIAOperacio.RESIDENCIA_LEGAL, peticion);
        RespostaConsultaDadesResidenciaLegal resposta = (RespostaConsultaDadesResidenciaLegal) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}