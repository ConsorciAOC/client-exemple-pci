package cat.aoc.client_pci;

import cat.aoc.client_pci.soap.Clients;
import net.aocat.padro.PeticionResidente;
import net.aocat.padro.RespuestaResidente;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PADROTest {
    private static final String SERVEI = "PADRO";
    private static final String FINALITAT = "PROVES";

    @DisplayName("RESIDENT")
    @Test
    void resident() throws Exception {
        PeticionBuilder builder = new PeticionBuilderFromProperties("src\\main\\resources\\client.properties");
        Peticion peticion = builder.build(
                SERVEI,
                "RESIDENT",
                FINALITAT,
                buildPeticionResidente()
        );
        Respuesta response = Clients.PADRO.getClient(Entorn.PRE).send(peticion);
        assertNotNull(response);
        RespuestaResidente specificResponse = (RespuestaResidente) response.getTransmisiones()
                .getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(specificResponse);
        assertNotNull(specificResponse.getDocumentacion());
        assertEquals(1, specificResponse.getCodigoResultado()); // consta
    }

    private static PeticionResidente buildPeticionResidente(){
        PeticionResidente dades = new PeticionResidente();
        dades.setNumExpediente("test");
        dades.setTipoDocumentacion(1); // NIF
        dades.setDocumentacion("21777952B");
        return dades;
    }

}
