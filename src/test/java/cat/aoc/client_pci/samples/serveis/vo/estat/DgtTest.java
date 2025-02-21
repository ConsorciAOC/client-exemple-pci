package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgt.OperacioDgt;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgt.PeticionBuilderDgt;
import generated.serveis.dgt.RespostaConsultaVehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DgtTest extends AbstractClientPCITest<OperacioDgt> {

    public DgtTest() throws IOException {
        super(Serveis.DGT.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderDgt(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("DGT_DADES_VEHICLE")
    void dadesVehicle() throws ClientException {
        RespostaConsultaVehicle resposta = this.send(OperacioDgt.DGT_DADES_VEHICLE, "9821920002_PROVES");
        assertNotNull(resposta);
        assertNotNull(resposta.getResposta());
    }

}