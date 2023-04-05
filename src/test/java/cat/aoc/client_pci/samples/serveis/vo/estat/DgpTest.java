package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgp.OperacioDgp;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgp.PeticionBuilderDgp;
import generated.serveis.dgp.RespostaConsultaDadesIdentitat;
import generated.serveis.dgp.RespostaVerificacioDadesIdentitat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DgpTest extends AbstractClientPCITest<OperacioDgp> {

    public DgpTest() throws IOException {
        super(Serveis.DGP.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderDgp(PROPERTIES));
    }

    @Test
    @DisplayName("IDENTITAT_DADES")
    void identitatDades() throws ClientException {
        RespostaConsultaDadesIdentitat resposta = this.send(OperacioDgp.IDENTITAT_DADES, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getDadesTitular());
    }

    @Test
    @DisplayName("IDENTITAT_VERIFICACIO")
    void identitatVerificacio() throws ClientException {
        RespostaVerificacioDadesIdentitat resposta = this.send(OperacioDgp.IDENTITAT_VERIFICACIO, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}