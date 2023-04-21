package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.estrangeria.OperacioEstrangeria;
import cat.aoc.client_pci.samples.serveis.vo.estat.estrangeria.PeticionBuilderEstrangeria;
import generated.serveis.estrangeria.RespostaConsultaDadesResidenciaLegal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EstrangeriaTest extends AbstractClientPCITest<OperacioEstrangeria> {

    public EstrangeriaTest() throws IOException {
        super(Serveis.ESTRANGERIA.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderEstrangeria(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("RESIDENCIA_LEGAL")
    void residenciaLegal() throws ClientException {
        RespostaConsultaDadesResidenciaLegal resposta = this.send(OperacioEstrangeria.RESIDENCIA_LEGAL, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}