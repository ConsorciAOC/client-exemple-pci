package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.sir2.OperacioSir2;
import cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2;
import generated.serveis.sir2.RespostaConsultaAssentament;
import generated.serveis.sir2.RespostaEnviamentAssentament;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class Sir2Test extends AbstractClientPCITest<OperacioSir2> {

    public Sir2Test() throws IOException {
        super(Serveis.SIR2.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderSir2(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("CONSULTAR")
    void consultar() throws ClientException {
        RespostaConsultaAssentament resposta = this.send(OperacioSir2.CONSULTAR, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getResultat());
    }

    @Test
    @DisplayName("ENVIAR")
    void enviar() throws ClientException {
        RespostaEnviamentAssentament resposta = this.send(OperacioSir2.ENVIAR, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getResultat());
    }

}