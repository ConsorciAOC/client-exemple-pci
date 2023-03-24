package cat.aoc.client_pci.clients.vo.estat.dependencia;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.dependencia.RespostaConsultaNivellGrauDependencia;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DEPENDENCIAClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private DEPENDENCIAClient client;

    @BeforeEach
    void setUp() {
        client = new DEPENDENCIAClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (DEPENDENCIAOperacio operacio : DEPENDENCIAOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new DEPENDENCIAPeticionBuilder(PROPERTIES_PATH).build(DEPENDENCIAOperacio.GRAU_DEPENDENCIA, Finalitat.PROVES);
        Respuesta respuesta = client.send(DEPENDENCIAOperacio.GRAU_DEPENDENCIA, peticion);
        RespostaConsultaNivellGrauDependencia resposta = (RespostaConsultaNivellGrauDependencia) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }
    
}