package cat.aoc.client_pci.clients.vo.generalitat.soc;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.soc.CertUltimPeriodeResponse;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SOCClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private SOCClient client;

    @BeforeEach
    void setUp() {
        client = new SOCClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (SOCOperacio operacio : SOCOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new SOCPeticionBuilder(PROPERTIES_PATH).build(SOCOperacio.SOC_CERT_ULTIMPERIODE, Finalitat.PROVES);
        Respuesta respuesta = client.send(SOCOperacio.SOC_CERT_ULTIMPERIODE, peticion);
        CertUltimPeriodeResponse resposta = (CertUltimPeriodeResponse) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}
