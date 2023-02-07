package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.tfn.RespostaDadesCompletes;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TFNClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private TFNClient client;
    @BeforeEach
    void setUp() {
        client = new TFNClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() {
        for (TFNOperacio operacio : TFNOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws NotFoundException {
        Peticion peticion = new TFNPeticionBuilder(PROPERTIES_PATH).build(TFNOperacio.TFN_DADESCOMPLETES, Finalitat.PROVES);
        Respuesta respuesta = client.send(TFNOperacio.TFN_DADESCOMPLETES, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaDadesCompletes dadesCompletes = (RespostaDadesCompletes) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(dadesCompletes);
        assertEquals("38991311D", dadesCompletes.getPeticioDadesCompletes().getIdentificadorTitular().getDocumentacio());
        assertEquals("CARRER PROVA1", dadesCompletes.getResposta().getNomVia());
    }

}
