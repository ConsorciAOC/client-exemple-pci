package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.estat.mepsyd.OperacioMepsyd;
import cat.aoc.client_pci.samples.serveis.vo.estat.mepsyd.PeticionBuilderMepsyd;
import generated.serveis.mepsyd.RespostaVerificacioTitolsUniversitaris;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MepsydTest extends AbstractClientPCITest<OperacioMepsyd> {

    public MepsydTest() throws IOException {
        super(Serveis.MEPSYD.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderMepsyd(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("TITOLS_UNIVERSITARIS")
    void titolsUniversitaris() throws ClientException {
        RespostaVerificacioTitolsUniversitaris resposta = this.send(OperacioMepsyd.TITOLS_UNIVERSITARIS, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getTitolsUniversitaris());
    }

}