package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rgc.OperacioRgc;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.rgc.PeticionBuilderRgc;
import generated.serveis.rgc.RespostaConsultaPrestacions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RgcTest extends AbstractClientPCITest<OperacioRgc> {
    public RgcTest() throws IOException {
        super(Serveis.RGC.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderRgc(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("RGC_CONSULTA")
    void consulta() throws ClientException {
        RespostaConsultaPrestacions resposta = this.send(OperacioRgc.RGC_CONSULTA, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
