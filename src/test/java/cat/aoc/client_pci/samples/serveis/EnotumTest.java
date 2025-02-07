package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.enotum.OperacioEnotum;
import cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotum;
import generated.serveis.enotum.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EnotumTest extends AbstractClientPCITest<OperacioEnotum> {

    public EnotumTest() throws IOException {
        super(Serveis.ENOTUM.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderEnotum(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("PROCESSAR_TRAMESA")
    void processarTramesa() throws ClientException {
        RespostaProcessarTramesa resposta = this.send(OperacioEnotum.PROCESSAR_TRAMESA, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getNotificacionsCreades().getIdNotificacio().get(0));
    }

    @Test
    @DisplayName("CERCA")
    void cerca() throws ClientException {
        RespostaCerca resposta = this.send(OperacioEnotum.CERCA, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals(25, resposta.getResultats().getNotificacio().size());
    }

    @Test
    @DisplayName("CONSULTA")
    void consulta() throws ClientException {
        RespostaConsulta resposta = this.send(OperacioEnotum.CONSULTA, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getIdNotificacio());
    }

    @Test
    @DisplayName("RESUM")
    void resum() throws ClientException {
        RespostaResum resposta = this.send(OperacioEnotum.RESUM, Finalitat.PROVES);
        assertNotNull(resposta);
        assertEquals(0, resposta.getNotificacionsEntitat().size());
    }

    @Test
    @DisplayName("PARAULA_PAS")
    void paraulaPas() throws ClientException {
        RespostaParaulaPas resposta = this.send(OperacioEnotum.PARAULA_PAS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}