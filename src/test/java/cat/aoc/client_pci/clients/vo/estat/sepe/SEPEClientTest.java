package cat.aoc.client_pci.clients.vo.estat.sepe;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.sepe.RespostaVerificacioDadesAtur;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SEPEClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private SEPEClient client;

    @BeforeEach
    void setUp() {
        client = new SEPEClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (SEPEOperacio operacio : SEPEOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new SEPEPeticionBuilder(PROPERTIES_PATH).build(SEPEOperacio.VERIF_DADES_ATUR, Finalitat.PROVES);
        Respuesta respuesta = client.send(SEPEOperacio.VERIF_DADES_ATUR, peticion);
        RespostaVerificacioDadesAtur resposta = (RespostaVerificacioDadesAtur) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}