package cat.aoc.client_pci.clients.tfm;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.tfm.RespostaDadesCompletes;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TFMClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private TFMClient client;
    @BeforeEach
    void setUp() {
        client = new TFMClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (TFMOperacio operacio : TFMOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new TFMPeticionBuilder(PROPERTIES_PATH).build(TFMOperacio.TFM_DADESCOMPLETES, Finalitat.PROVES);
        Respuesta respuesta = client.send(TFMOperacio.TFM_DADESCOMPLETES, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaDadesCompletes dadesCompletes = (RespostaDadesCompletes) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(dadesCompletes);
        assertEquals("55564256M", dadesCompletes.getPeticioDadesCompletes().getIdentificadorTitular().getDocumentacio());
        assertEquals("Nif1", dadesCompletes.getResposta().getNomVia());
    }

}