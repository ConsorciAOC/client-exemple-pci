package cat.aoc.client_pci.clients.vo.generalitat.atc;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.atc.InfDeutePICAResponse;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.peticion.Titular;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ATCClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private ATCClient client;

    @BeforeEach
    void setUp() {
        client = new ATCClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (ATCOperacio operacio : ATCOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new ATCPeticionBuilder(PROPERTIES_PATH).build(ATCOperacio.ATC_INF_DEUTES_TMP, Finalitat.PROVES);
        Titular titular = new Titular();
        titular.setTipoDocumentacion("DNI");
        titular.setDocumentacion("10000949C");
        titular.setNombre("OLGA");
        titular.setApellido1("MIGUEL");
        titular.setApellido2("CHAO");
        titular.setNombreCompleto("OLGA MIGUEL CHAO");
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(titular);
        Respuesta respuesta = client.send(ATCOperacio.ATC_INF_DEUTES_TMP, peticion);
        InfDeutePICAResponse resposta = (InfDeutePICAResponse) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }
    
}
