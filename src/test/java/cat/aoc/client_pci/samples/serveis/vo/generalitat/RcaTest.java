package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rca.OperacioRca;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rca.PeticionBuilderRca;
import generated.rca.RespostaVerificacioAssegurat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RcaTest extends AbstractClientPCITest<OperacioRca> {
    public RcaTest() throws IOException {
        super(Serveis.RCA.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderRca(PROPERTIES));
    }

    @Test
    @DisplayName("RCA_VERIFICACIO")
    void verificacio() throws ClientException {
        RespostaVerificacioAssegurat resposta = this.send(OperacioRca.RCA_VERIFICACIO, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
