package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.boe.OperacioBoe;
import cat.aoc.client_pci.samples.serveis.boe.PeticionBuilderBoe;
import generated.serveis.boe.RespostaEnviamentAnunci;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BoeTest extends AbstractClientPCITest<OperacioBoe> {

    public BoeTest() throws IOException {
        super(Serveis.BOE.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderBoe(PROPERTIES));
    }

    @Test
    @DisplayName("CONSULTAR")
    void consultar() throws ClientException {
        RespostaEnviamentAnunci resposta = this.send(OperacioBoe.CONSULTAR, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}