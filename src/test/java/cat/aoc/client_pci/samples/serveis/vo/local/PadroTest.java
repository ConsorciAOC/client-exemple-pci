package cat.aoc.client_pci.samples.serveis.vo.local;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import generated.serveis.padro.RespuestaDatosConvivientes;
import generated.serveis.padro.RespuestaDatosTitular;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PadroTest extends AbstractClientPCITest<OperacioPadro> {

    public PadroTest() throws IOException {
        super(Serveis.PADRO.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderPadro(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("TITULAR")
    void titular() throws ClientException {
        RespuestaDatosTitular resposta = this.send(OperacioPadro.TITULAR, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals("12345678Z", resposta.getDocumentacion());
        assertEquals(1, resposta.getCodigoResultado());
        generated.serveis.padro.empadronamiento.VolanteEmpadronamiento volante = (generated.serveis.padro.empadronamiento.VolanteEmpadronamiento) resposta.getAny();
        assertEquals("Friedrich", volante.getVolanteEmpadronamientoDatosFirmados().getDatosPersonales().getNombre());
    }

    @Test
    @DisplayName("CONVIVENTS")
    void convivents() throws ClientException {
        RespuestaDatosConvivientes resposta = this.send(OperacioPadro.CONVIVENTS, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals("12345678Z", resposta.getDocumentacion());
        assertEquals(1, resposta.getCodigoResultado());
        generated.serveis.padro.convivencia.VolanteEmpadronamiento volante = (generated.serveis.padro.convivencia.VolanteEmpadronamiento) resposta.getAny();
        assertEquals("Friedrich", volante.getVolanteEmpadronamientoDatosFirmados().getDatosPersonales().getNombre());
        assertEquals("3", volante.getVolanteEmpadronamientoDatosFirmados().getNumeroAcompanantes().toString());
    }

}
