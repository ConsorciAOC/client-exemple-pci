package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.model.Finalitat;
import generated.etauler.RespostaConsultarEstatEdicte;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ETAULERClientTest {

    @Test
    void getFrontal() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ETAULER.getClient(Entorn.PRE);
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.PUBLICAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.DADES));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.AMPLIAR_TERMINI));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.CANCELAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.DESPENJAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.SINCRONITZAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.DESCARREGAR_DOCUMENT));
        assertEquals(Frontal.SINCRON, client.getFrontal(ETAULEROperacio.CONSULTAR));
    }

    @Test
    void getCodiServei() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ETAULER.getClient(Entorn.PRE);
        assertEquals("ETAULER", client.getCodiServei());
    }

    @Test
    void getCodiModalitat() throws NotDefinedException, NotFoundException {
        ClientAOC client = Clients.ETAULER.getClient(Entorn.PRE);
        for (ETAULEROperacio operacio : ETAULEROperacio.values()) {
            assertEquals("ETAULER", client.getCodiModalitat(operacio));
        }
    }
    @Test
    void send() throws NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.ETAULER.getClient(Entorn.PRE)
                .send(ETAULEROperacio.CONSULTAR, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaConsultarEstatEdicte respostaConsultarEstatEdicte = (RespostaConsultarEstatEdicte) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respostaConsultarEstatEdicte);
        assertEquals("2021-10-08+02:00", respostaConsultarEstatEdicte.getDataFiPublicacioEfectiva().toString());
        assertEquals("2021-10-08+02:00", respostaConsultarEstatEdicte.getDataFiPublicacioEfectiva().toString());
    }

}