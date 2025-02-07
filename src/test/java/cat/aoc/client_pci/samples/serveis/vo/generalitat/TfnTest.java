package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.tfn.OperacioTfn;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.tfn.PeticionBuilderTfn;
import generated.serveis.tfn.RespostaDadesCompletes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TfnTest extends AbstractClientPCITest<OperacioTfn> {
    public TfnTest() throws IOException {
        super(Serveis.TFN.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderTfn(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("TFN_DADESCOMPLETES")
    void dadesCompletes() throws ClientException {
        RespostaDadesCompletes resposta = this.send(OperacioTfn.TFN_DADESCOMPLETES, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals("38991311D", resposta.getPeticioDadesCompletes().getIdentificadorTitular().getDocumentacio());
        assertEquals("CARRER PROVA1", resposta.getResposta().getNomVia());
    }

}
