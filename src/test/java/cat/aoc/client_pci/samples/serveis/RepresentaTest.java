package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.representa.OperacioRepresenta;
import cat.aoc.client_pci.samples.serveis.representa.PeticionBuilderRepresenta;
import generated.serveis.representa.ConsultarRepresentacioResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepresentaTest extends AbstractClientPCITest<OperacioRepresenta> {

    public RepresentaTest() throws IOException {
        super(Serveis.REPRESENTA.getClient(Entorn.PRE, Frontal.SINCRON, KEYSTORE_PATH),
                new PeticionBuilderRepresenta(CLIENT_BUILDER_PROPERTIES));
    }

    @Test
    @DisplayName("CONSULTA_REPRESENTACIO")
    void consultarRepresentacio() throws ClientException {
        ConsultarRepresentacioResponse resposta = this.send(OperacioRepresenta.CONSULTA_REPRESENTACIO, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}