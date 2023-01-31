package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.enotum.RespostaProcessarTramesa;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ENOTUMClientTest {

    @Test
    void getFrontal() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.CONSULTA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.EVIDENCIA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PRACTICAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PARAULA_PAS));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PROCESSAR_TRAMESA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.RECUPERAR_REPORT));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.RESUM));
    }

    @Test
    void getCodiServei() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        assertEquals("ENOTUM", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        for (ENOTUMOperacio operacio : ENOTUMOperacio.values()) {
            assertEquals("ENOTUM", client.getCodiModalitat(operacio));
        }
    }

    @Test
    void send() throws NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.ENOTUM.getClient(Entorn.PRE)
                .send(ENOTUMOperacio.PROCESSAR_TRAMESA, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaProcessarTramesa processarTramesa = (RespostaProcessarTramesa) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getNotificacionsCreades().getIdNotificacio().get(0));
    }

}