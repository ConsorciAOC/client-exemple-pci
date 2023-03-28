package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rpe.OperacioRpe;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rpe.PeticionBuilderRpe;
import generated.rpe.RespostaConsultaParellesEstables;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RpeTest extends AbstractClientPCITest<OperacioRpe> {
    public RpeTest() throws IOException {
        super(Serveis.RPE.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderRpe(PROPERTIES));
    }

    @Test
    @DisplayName("RPE_CONSULTA")
    void consulta() throws ClientException {
        RespostaConsultaParellesEstables resposta = this.send(OperacioRpe.RPE_CONSULTA, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
