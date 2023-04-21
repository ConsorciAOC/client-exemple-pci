package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.inss.OperacioInss;
import cat.aoc.client_pci.samples.serveis.vo.estat.inss.PeticionBuilderInss;
import generated.serveis.inss.RespostaConsultaPrestacions;
import generated.serveis.inss_historic.RespostaConsultaPrestacionsHistoric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InssTest extends AbstractClientPCITest<OperacioInss> {

    public InssTest() throws IOException {
        super(Serveis.INSS.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderInss(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("PRESTACIONS")
    void prestacions() throws ClientException {
        RespostaConsultaPrestacions resposta = this.send(OperacioInss.PRESTACIONS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

    @Test
    @DisplayName("PRESTACIONS_HISTORIC")
    void prestacionsHistoric() throws ClientException {
        RespostaConsultaPrestacionsHistoric resposta = this.send(OperacioInss.PRESTACIONS_HISTORIC, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}