package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.over.OperacioOver;
import cat.aoc.client_pci.samples.serveis.over.PeticionBuilderOver;
import generated.over.RespostaDocumentacioTramit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OverTest extends AbstractClientPCITest<OperacioOver> {

    public OverTest() throws IOException {
        super(Serveis.OVER.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderOver(PROPERTIES));
    }
    @Test
    @DisplayName("OVER_DOCUMENTACIO")
    void documentacio() throws ClientException {
        RespostaDocumentacioTramit resposta = this.send(OperacioOver.OVER_DOCUMENTACIO, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
