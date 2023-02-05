package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.enotum.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ENOTUMClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private ENOTUMClient client;
    @BeforeEach
    void setUp() {
        client = new ENOTUMClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws NotDefinedException {
        for (ENOTUMOperacio operacio : ENOTUMOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void getCodiServei() {
        assertEquals("ENOTUM", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() {
        for (ENOTUMOperacio operacio : ENOTUMOperacio.values()) {
            assertEquals("ENOTUM", client.getCodiModalitat(operacio));
        }
    }

    @Test
    void processarTramesa() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ENOTUMPeticionBuilder(PROPERTIES_PATH).build(ENOTUMOperacio.PROCESSAR_TRAMESA, Finalitat.PROVES);
        Respuesta respuesta = client.send(ENOTUMOperacio.PROCESSAR_TRAMESA, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaProcessarTramesa processarTramesa = (RespostaProcessarTramesa) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getNotificacionsCreades().getIdNotificacio().get(0));
    }

    @Test
    void cerca() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ENOTUMPeticionBuilder(PROPERTIES_PATH).build(ENOTUMOperacio.CERCA, Finalitat.PROVES);
        Respuesta respuesta = client.send(ENOTUMOperacio.CERCA, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaCerca respostaCerca = (RespostaCerca) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaCerca);
        assertEquals(25, respostaCerca.getResultats().getNotificacio().size());
    }

    @Test
    void consulta() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ENOTUMPeticionBuilder(PROPERTIES_PATH).build(ENOTUMOperacio.CONSULTA, Finalitat.PROVES);
        Respuesta respuesta = client.send(ENOTUMOperacio.CONSULTA, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaConsulta respostaConsulta = (RespostaConsulta) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaConsulta);
        assertNotNull(respostaConsulta.getIdNotificacio());
    }

    @Test
    void resum() throws NotDefinedException, NotFoundException {
        Peticion peticion = new ENOTUMPeticionBuilder(PROPERTIES_PATH).build(ENOTUMOperacio.RESUM, Finalitat.PROVES);
        Respuesta respuesta = client.send(ENOTUMOperacio.RESUM, peticion);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaResum respostaResum = (RespostaResum) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaResum);
        assertEquals(0, respostaResum.getNotificacionsEntitat().size());
    }

}