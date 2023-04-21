package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.registre_entitats.OperacioRegistreEntitats;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.registre_entitats.PeticionBuilderRegistreEntitats;
import generated.serveis.registre_entitats.RespostaInscripcionsEntitats;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistreEntitatsTest extends AbstractClientPCITest<OperacioRegistreEntitats> {
    public RegistreEntitatsTest() throws IOException {
        super(Serveis.REGISTRE_ENTITATS.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderRegistreEntitats(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("ENTITAT_INSCRIPCIO")
    void inscripcio() throws ClientException {
        RespostaInscripcionsEntitats resposta = this.send(OperacioRegistreEntitats.ENTITAT_INSCRIPCIO, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
