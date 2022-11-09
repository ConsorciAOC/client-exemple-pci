package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.enotum.PeticioProcessarTramesa;
import generated.enotum.RespostaProcessarTramesa;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ENOTUMClientTest {

    @Test
    void getFrontal() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.CERCA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.CONSULTA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.EVIDENCIA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PRACTICAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PARAULA_PAS));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PROCESSAR_TRAMESA));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.RECUPERAR_REPORT));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.RESUM));
    }

    @Test
    void getPeticion() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        Peticion peticion = client.getPeticion(ENOTUMOperacio.PROCESSAR_TRAMESA, Finalitat.PROVES);
        assertNotNull(peticion.getAtributos().getIdPeticion());
        PeticioProcessarTramesa processarTramesa = (PeticioProcessarTramesa) peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getTramesa());
        assertEquals("REF", processarTramesa.getTramesa().getNotificacio().get(0).getReferencia());
    }

    @Test
    void send() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.ENOTUM.getClient(Entorn.PRE)
                .send(ENOTUMOperacio.PROCESSAR_TRAMESA, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaProcessarTramesa processarTramesa = (RespostaProcessarTramesa) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getNotificacionsCreades().getIdNotificacio().get(0));
    }

}