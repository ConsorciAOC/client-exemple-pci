package cat.aoc.client_pci.clients.vo.estat.dgp;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.dgp.RespostaConsultaDadesIdentitat;
import generated.dgp.RespostaVerificacioDadesIdentitat;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.peticion.Titular;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DGPClientTest {
    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private DGPClient client;

    @BeforeEach
    void setUp() {
        client = new DGPClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (DGPOperacio operacio : DGPOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void identitatDades() throws ClientException {
        Peticion peticion = new DGPPeticionBuilder(PROPERTIES_PATH).build(DGPOperacio.IDENTITAT_DADES, Finalitat.PROVES);
        Titular titular = new Titular();
        titular.setTipoDocumentacion("DNI");
        titular.setDocumentacion("10000949C");
        titular.setNombre("OLGA");
        titular.setApellido1("MIGUEL");
        titular.setApellido2("CHAO");
        titular.setNombreCompleto("OLGA MIGUEL CHAO");
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(titular);
        Respuesta respuesta = client.send(DGPOperacio.IDENTITAT_DADES, peticion);
        RespostaConsultaDadesIdentitat resposta = (RespostaConsultaDadesIdentitat) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
        assertNotNull(resposta.getDadesTitular());
    }

    @Test
    void identitatVerificacio() throws ClientException {
        Peticion peticion = new DGPPeticionBuilder(PROPERTIES_PATH).build(DGPOperacio.IDENTITAT_VERIFICACIO, Finalitat.PROVES);
        Titular titular = new Titular();
        titular.setTipoDocumentacion("DNI");
        titular.setDocumentacion("10000949C");
        titular.setNombre("OLGA");
        titular.setApellido1("MIGUEL");
        titular.setApellido2("CHAO");
        titular.setNombreCompleto("OLGA MIGUEL CHAO");
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(titular);
        Respuesta respuesta = client.send(DGPOperacio.IDENTITAT_VERIFICACIO, peticion);
        RespostaVerificacioDadesIdentitat resposta = (RespostaVerificacioDadesIdentitat) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}