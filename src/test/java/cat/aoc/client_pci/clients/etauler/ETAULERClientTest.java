package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.etauler.RespostaConsultarEstatEdicte;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ETAULERClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private ETAULERClient client;
    @BeforeEach
    void setUp() {
        client = new ETAULERClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws NotDefinedException {
        for (ETAULEROperacio operacio : ETAULEROperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void getCodiServei() {
        assertEquals("ETAULER", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() {
        for (ETAULEROperacio operacio : ETAULEROperacio.values()) {
            assertEquals("ETAULER", client.getCodiModalitat(operacio));
        }
    }

    @Test
    void send() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ETAULERPeticionBuilder(PROPERTIES_PATH).build(ETAULEROperacio.CONSULTAR, Finalitat.PROVES);
        Respuesta respuesta = client.send(ETAULEROperacio.CONSULTAR, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaConsultarEstatEdicte respostaConsultarEstatEdicte = (RespostaConsultarEstatEdicte) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaConsultarEstatEdicte);
        assertEquals("2021-10-08+02:00", respostaConsultarEstatEdicte.getDataFiPublicacioEfectiva().toString());
        assertEquals("2021-10-08+02:00", respostaConsultarEstatEdicte.getDataFiPublicacioEfectiva().toString());
    }

}