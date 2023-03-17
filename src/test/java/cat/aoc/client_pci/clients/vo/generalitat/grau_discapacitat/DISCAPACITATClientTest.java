package cat.aoc.client_pci.clients.vo.generalitat.grau_discapacitat;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.grau_discapacitat.RespostaConsultaDiscapacitatSimple;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DISCAPACITATClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private DISCAPACITATClient client;

    @BeforeEach
    void setUp() {
        client = new DISCAPACITATClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (DISCAPACITATOperacio operacio : DISCAPACITATOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new DISCAPACITATPeticionBuilder(PROPERTIES_PATH).build(DISCAPACITATOperacio.GRAU_DISCAPACITAT_SIMPLE, Finalitat.PROVES);
        Respuesta respuesta = client.send(DISCAPACITATOperacio.GRAU_DISCAPACITAT_SIMPLE, peticion);
        RespostaConsultaDiscapacitatSimple resposta = (RespostaConsultaDiscapacitatSimple) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }
    
}