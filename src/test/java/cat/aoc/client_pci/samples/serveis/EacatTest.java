package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.eacat.OperacioEacat;
import cat.aoc.client_pci.samples.serveis.eacat.PeticionBuilderEacat;
import generated.eacat.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EacatTest extends AbstractClientPCITest<OperacioEacat> {

    public EacatTest() throws IOException {
        super(Serveis.EACAT.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderEacat(PROPERTIES));
    }

    @Test
    @DisplayName("EACAT_USUARI")
    void usuari() throws ClientException {
        RespostaConsultaUsuari resposta = this.send(OperacioEacat.EACAT_USUARI, Finalitat.PROVES);
        assertNotNull(resposta);
    }

    @Test
    @DisplayName("EACAT_SERVEI")
    void servei() throws ClientException {
        RespostaConsultaServei resposta = this.send(OperacioEacat.EACAT_SERVEI, Finalitat.PROVES);
        assertNotNull(resposta);
    }

    @Test
    @DisplayName("EACAT_ENS")
    void ens() throws ClientException {
        RespostaConsultaEns resposta = this.send(OperacioEacat.EACAT_ENS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

    @Test
    @DisplayName("EACAT_TIPUS_ENS")
    void tipusEns() throws ClientException {
        RespostaConsultaTipusEns resposta = this.send(OperacioEacat.EACAT_TIPUS_ENS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
