package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.padro_historic.OperacioPadroHistoric;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.padro_historic.PeticionBuilderPadroHistoric;
import generated.serveis.padro_historic.RespuestaDatosConvivientesHistorico;
import generated.serveis.padro_historic.RespuestaDatosTitularHistorico;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PadroHistoricTest extends AbstractClientPCITest<OperacioPadroHistoric> {
    public PadroHistoricTest() throws IOException {
        super(Serveis.PADRO_HISTORIC.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderPadroHistoric(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("TITULAR_HISTORIC")
    void titularHistoric() throws ClientException {
        RespuestaDatosTitularHistorico resposta = this.send(OperacioPadroHistoric.TITULAR_HISTORIC, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getPeriodo());
    }

    @Test
    @DisplayName("CONVIVENTS_HISTORIC")
    void conviventsHistoric() throws ClientException {
        RespuestaDatosConvivientesHistorico resposta = this.send(OperacioPadroHistoric.CONVIVENTS_HISTORIC, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getPeriodo());
    }

}
