package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.model.Finalitat;
import generated.tfn.PeticioDadesCompletes;
import generated.tfn.RespostaDadesCompletes;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TFNClientTest {

    @Test
    void getFrontal() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.TFN.getClient(Entorn.PRE);
        assertEquals(Frontal.SINCRON, client.getFrontal(TFNOperacio.TFN_DADESCOMPLETES));
        assertEquals(Frontal.SINCRON, client.getFrontal(TFNOperacio.TFN_DADESCOMPLETES_DIS));
        assertEquals(Frontal.SINCRON, client.getFrontal(TFNOperacio.TFN_VIGENCIA));
    }

    @Test
    void getPeticion() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.TFN.getClient(Entorn.PRE);
        Peticion peticion = client.getPeticion(TFNOperacio.TFN_DADESCOMPLETES, Finalitat.PROVES);
        assertNotNull(peticion.getAtributos().getIdPeticion());
        PeticioDadesCompletes dadesCompletes = (PeticioDadesCompletes) peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(dadesCompletes);
        assertNotNull(dadesCompletes.getIdentificadorTitular());
        assertEquals("38991311D", dadesCompletes.getIdentificadorTitular().getDocumentacio());
    }

    @Test
    void send() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.TFN.getClient(Entorn.PRE)
                .send(TFNOperacio.TFN_DADESCOMPLETES, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespostaDadesCompletes dadesCompletes = (RespostaDadesCompletes) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(dadesCompletes);
        assertEquals("38991311D", dadesCompletes.getPeticioDadesCompletes().getIdentificadorTitular().getDocumentacio());
        assertEquals("CARRER PROVA1", dadesCompletes.getResposta().getNomVia());
    }

}