package cat.aoc.client_pci.clients.vo.padro;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.padro.RespuestaDatosConvivientes;
import generated.padro.RespuestaDatosTitular;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PADROProxyClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private PADROProxyClient client;
    @BeforeEach
    void setUp() {
        client = new PADROProxyClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (PADROOperacio operacio : PADROOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void titular() throws ClientException {
        Peticion peticion = new PADROPeticionBuilder(PROPERTIES_PATH).build(PADROOperacio.TITULAR, Finalitat.PROVES);
        Respuesta respuesta = client.send(PADROOperacio.TITULAR, peticion);
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
    void convivents() throws ClientException {
        Peticion peticion = new PADROPeticionBuilder(PROPERTIES_PATH).build(PADROOperacio.CONVIVENTS, Finalitat.PROVES);
        Respuesta respuesta = client.send(PADROOperacio.CONVIVENTS, peticion);
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
