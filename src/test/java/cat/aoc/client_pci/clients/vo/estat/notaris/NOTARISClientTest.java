package cat.aoc.client_pci.clients.vo.estat.notaris;

import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.exceptions.ClientException;
import generated.notaris.RespostaConsultaSubsistenciaAdministradors;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NOTARISClientTest {

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private NOTARISClient client;

    @BeforeEach
    void setUp() {
        client = new NOTARISClient(KEYSTORE_PATH, Entorn.PRE);
    }

    @Test
    void getFrontal() throws ClientException {
        for (NOTARISOperacio operacio : NOTARISOperacio.values()) {
            assertEquals(Frontal.SINCRON, client.getFrontal(operacio));
        }
    }

    @Test
    void send() throws ClientException {
        Peticion peticion = new NOTARISPeticionBuilder(PROPERTIES_PATH).build(NOTARISOperacio.SUBSISTENCIA_ADMINISTRADORS, Finalitat.PROVES);
        Respuesta respuesta = client.send(NOTARISOperacio.SUBSISTENCIA_ADMINISTRADORS, peticion);
        RespostaConsultaSubsistenciaAdministradors resposta = (RespostaConsultaSubsistenciaAdministradors) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
        assertNotNull(resposta);
    }

}