package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.tfm.OperacioTfm;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.tfm.PeticionBuilderTfm;
import generated.serveis.tfm.RespostaDadesCompletes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TfmTest extends AbstractClientPCITest<OperacioTfm> {
    public TfmTest() throws IOException {
        super(Serveis.TFM.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderTfm(PROPERTIES));
    }

    @Test
    @DisplayName("TFM_DADESCOMPLETES")
    void dadesCompletes() throws ClientException {
        RespostaDadesCompletes resposta = this.send(OperacioTfm.TFM_DADESCOMPLETES, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals("55564256M", resposta.getPeticioDadesCompletes().getIdentificadorTitular().getDocumentacio());
        assertEquals("Nif1", resposta.getResposta().getNomVia());
    }

}