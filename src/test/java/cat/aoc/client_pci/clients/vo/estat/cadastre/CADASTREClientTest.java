package cat.aoc.client_pci.clients.vo.estat.cadastre;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.cadastre.certificacio.RespostaCertificacioTitular;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CADASTREClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";
    private CADASTREClient client;
    @BeforeEach
    void setUp() {
        client = new CADASTREClient(KEYSTORE_PATH, Entorn.PRE);
    }
    @Test
    void getFrontal() throws ClientException {
        for (CADASTREOperacio operacio : CADASTREOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }
    @Test
    void send() throws ClientException {
        Peticion peticion = new CADASTREPeticionBuilder(PROPERTIES_PATH).build(CADASTREOperacio.CERTIFICACIO_TITULARITAT, Finalitat.PROVES);
        Respuesta respuesta = client.send(CADASTREOperacio.CERTIFICACIO_TITULARITAT, peticion);
        RespostaCertificacioTitular resposta = (RespostaCertificacioTitular) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }
    
}