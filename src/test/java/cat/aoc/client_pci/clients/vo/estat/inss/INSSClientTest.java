package cat.aoc.client_pci.clients.vo.estat.inss;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.inss_historic.RespostaConsultaPrestacionsHistoric;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class INSSClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private INSSClient client;

    @BeforeEach
    void setUp() {
        client = new INSSClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (INSSOperacio operacio : INSSOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new INSSPeticionBuilder(PROPERTIES_PATH).build(INSSOperacio.PRESTACIONS_HISTORIC, Finalitat.PROVES);
        Respuesta respuesta = client.send(INSSOperacio.PRESTACIONS_HISTORIC, peticion);
        RespostaConsultaPrestacionsHistoric resposta = (RespostaConsultaPrestacionsHistoric) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}