package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.Clients;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import generated.padro.PeticionDatosTitular;
import generated.padro.RespuestaDatosConvivientes;
import generated.padro.RespuestaDatosTitular;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PADROClientTest {

    @Test
    void getFrontal() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.PADRO.getClient(Entorn.PRE);
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.MUNICIPI_RESIDENCIA));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.RESIDENT_MUNICIPI));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.NUMERO_CONVIVENTS));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.COMPROVACIO_CONVIVENTS));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.TITULAR));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.TITULAR_PROPI));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.CONVIVENTS));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.CONVIVENTS_PROPI));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.TITULAR_PDF));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.CONVIVENTS_PDF));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.TITULAR_IDESCAT));
        assertEquals(Frontal.SINCRON, client.getFrontal(PADROOperacio.CERCA_TITULAR));
    }

    @Test
    void getPeticion() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        ClientAOC client = Clients.PADRO.getClient(Entorn.PRE);
        Peticion peticion = client.getPeticion(PADROOperacio.TITULAR, Finalitat.PROVES);
        assertNotNull(peticion.getAtributos().getIdPeticion());
        PeticionDatosTitular peticionDatosTitular = (PeticionDatosTitular) peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(peticionDatosTitular);
        assertNotNull(peticionDatosTitular.getDocumentacion());
        assertEquals("12345678Z", peticionDatosTitular.getDocumentacion());
    }

    @Test
    void titular() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.PADRO.getClient(Entorn.PRE)
                .send(PADROOperacio.TITULAR, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespuestaDatosTitular respuestaDatosTitular = (RespuestaDatosTitular) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respuestaDatosTitular);
        assertEquals("12345678Z", respuestaDatosTitular.getDocumentacion());
        assertEquals(1, respuestaDatosTitular.getCodigoResultado());
        generated.padro.empadronamiento.VolanteEmpadronamiento volante = (generated.padro.empadronamiento.VolanteEmpadronamiento) respuestaDatosTitular.getAny();
        assertEquals("Friedrich", volante.getVolanteEmpadronamientoDatosFirmados().getDatosPersonales().getNombre());
    }

    @Test
    void convivents() throws WebServiceSupportException, NotDefinedException, NotFoundException {
        Respuesta respuesta = Clients.PADRO.getClient(Entorn.PRE)
                .send(PADROOperacio.CONVIVENTS, Finalitat.PROVES);
        assertNotNull(respuesta);
        assertNotNull(respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos());
        RespuestaDatosConvivientes respuestaDatosConvivientes = (RespuestaDatosConvivientes) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(respuestaDatosConvivientes);
        assertEquals("12345678Z", respuestaDatosConvivientes.getDocumentacion());
        assertEquals(1, respuestaDatosConvivientes.getCodigoResultado());
        generated.padro.convivencia.VolanteEmpadronamiento volante = (generated.padro.convivencia.VolanteEmpadronamiento) respuestaDatosConvivientes.getAny();
        assertEquals("Friedrich", volante.getVolanteEmpadronamientoDatosFirmados().getDatosPersonales().getNombre());
        assertEquals("3", volante.getVolanteEmpadronamientoDatosFirmados().getNumeroAcompanantes().toString());
    }

}