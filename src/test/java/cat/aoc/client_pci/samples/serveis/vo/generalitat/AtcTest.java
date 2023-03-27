package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.atc.OperacioAtc;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.atc.PeticionBuilderAtc;
import generated.atc.InfDeutePICAResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtcTest extends AbstractClientPCITest<OperacioAtc> {
    public AtcTest() throws IOException {
        super(Serveis.ATC.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderAtc(PROPERTIES));
    }

    @Test
    @DisplayName("ATC_INF_DEUTES_TMP")
    void infDeutesTmp() throws ClientException {
        InfDeutePICAResponse resposta = this.send(OperacioAtc.ATC_INF_DEUTES_TMP, Finalitat.PROVES);
        assertNotNull(resposta);
    }
    
}
