package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.cadastre.OperacioCadastre;
import cat.aoc.client_pci.samples.serveis.vo.estat.cadastre.PeticionBuilderCadastre;
import generated.serveis.cadastre.certificacio.RespostaCertificacioTitular;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CadastreTest extends AbstractClientPCITest<OperacioCadastre> {
    public CadastreTest() throws IOException {
        super(Serveis.CADASTRE.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderCadastre(PROPERTIES));
    }

    @Test
    @DisplayName("CERTIFICACIO_TITULARITAT")
    void certificacioTitularitat() throws ClientException {
        RespostaCertificacioTitular resposta = this.send(OperacioCadastre.CERTIFICACIO_TITULARITAT, Finalitat.PROVES);
        assertNotNull(resposta);
    }
    
}