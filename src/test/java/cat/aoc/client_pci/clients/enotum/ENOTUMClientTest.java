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
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioCerca));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioConsulta));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioEvidencia));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioPracticar));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioParaulaPas));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioProcessarTramesa));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioRecuperarReport));
        assertEquals(Frontal.SINCRON, client.getFrontal(ENOTUMOperacio.PeticioResum));
    }

    @Test
    void getPeticion() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ENOTUM.getClient(Entorn.PRE);
        Peticion peticion = client.getPeticion(ENOTUMOperacio.PeticioProcessarTramesa, Finalitat.PROVES);
        assertNotNull(peticion.getAtributos().getIdPeticion());
        PeticioProcessarTramesa processarTramesa = (PeticioProcessarTramesa) peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getTramesa());
        assertEquals("REF", processarTramesa.getTramesa().getNotificacio().get(0).getReferencia());
    }

    @Test
    void send() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.ENOTUM.getClient(Entorn.PRE)
                .send(ENOTUMOperacio.PeticioProcessarTramesa, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaProcessarTramesa processarTramesa = (RespostaProcessarTramesa) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(processarTramesa);
        assertNotNull(processarTramesa.getNotificacionsCreades().getIdNotificacio().get(0));
    }

}