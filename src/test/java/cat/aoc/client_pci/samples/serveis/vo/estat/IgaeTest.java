package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.estat.igae.OperacioIgae;
import cat.aoc.client_pci.samples.serveis.vo.estat.igae.PeticionBuilderIgae;
import generated.serveis.igae.RespostaConsultaInhabilitacions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class IgaeTest extends AbstractClientPCITest<OperacioIgae> {

    public IgaeTest() throws IOException {
        super(Serveis.IGAE.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderIgae(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("IGAE_INHABILITACIONS")
    void inhabilitacions() throws ClientException {
        RespostaConsultaInhabilitacions resposta = this.send(OperacioIgae.IGAE_INHABILITACIONS, "9821920002_PROVES");
        assertNotNull(resposta);
    }

}