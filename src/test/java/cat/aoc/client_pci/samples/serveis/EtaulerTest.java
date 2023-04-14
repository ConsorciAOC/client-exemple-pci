package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.etauler.OperacioEtauler;
import cat.aoc.client_pci.samples.serveis.etauler.PeticionBuilderEtauler;
import generated.serveis.etauler.RespostaPublicarEdicte;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EtaulerTest extends AbstractClientPCITest<OperacioEtauler> {

    public EtaulerTest() throws IOException {
        super(Serveis.ETAULER.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderEtauler(PROPERTIES));
    }

    @Test
    @DisplayName("PUBLICAR")
    void publicar() throws ClientException {
        RespostaPublicarEdicte resposta = this.send(OperacioEtauler.PUBLICAR, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getResultat());
    }


}