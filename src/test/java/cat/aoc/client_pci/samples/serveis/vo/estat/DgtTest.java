package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgt.OperacioDgt;
import cat.aoc.client_pci.samples.serveis.vo.estat.dgt.PeticionBuilderDgt;
import generated.dgp.RespostaConsultaDadesIdentitat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DgtTest extends AbstractClientPCITest<OperacioDgt> {

    public DgtTest() throws IOException {
        super(Serveis.DGT.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderDgt(PROPERTIES));
    }

    @Test
    @DisplayName("DGT_DADES_VEHICLE")
    void dadesVehicle() throws ClientException {
        RespostaConsultaDadesIdentitat resposta = this.send(OperacioDgt.DGT_DADES_VEHICLE, Finalitat.PROVES);
        assertNotNull(resposta);
        assertNotNull(resposta.getDadesTitular());
    }

}