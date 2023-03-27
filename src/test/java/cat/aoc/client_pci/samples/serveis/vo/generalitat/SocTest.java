package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.soc.OperacioSoc;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.soc.PeticionBuilderSoc;
import generated.soc.CertUltimPeriodeResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SocTest extends AbstractClientPCITest<OperacioSoc> {
    public SocTest() throws IOException {
        super(Serveis.SOC.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderSoc(PROPERTIES));
    }

    @Test
    @DisplayName("SOC_CERT_ULTIMPERIODE")
    void certUltimPeriode() throws ClientException {
        CertUltimPeriodeResponse resposta = this.send(OperacioSoc.SOC_CERT_ULTIMPERIODE, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
