package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.sepe.OperacioSepe;
import cat.aoc.client_pci.samples.serveis.vo.estat.sepe.PeticionBuilderSepe;
import generated.serveis.sepe.RespostaVerificacioDadesAtur;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SepeTest extends AbstractClientPCITest<OperacioSepe> {

    public SepeTest() throws IOException {
        super(Serveis.SEPE.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderSepe(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("VERIF_DADES_ATUR")
    void verifDadesAtur() throws ClientException {
        RespostaVerificacioDadesAtur resposta = this.send(OperacioSepe.VERIF_DADES_ATUR, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}