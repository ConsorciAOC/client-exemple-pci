package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.notaris.OperacioNotaris;
import cat.aoc.client_pci.samples.serveis.vo.estat.notaris.PeticionBuilderNotaris;
import generated.notaris.RespostaConsultaSubsistenciaAdministradors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotarisTest extends AbstractClientPCITest<OperacioNotaris> {

    public NotarisTest() throws IOException {
        super(Serveis.NOTARIS.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderNotaris(PROPERTIES));
    }

    @Test
    @DisplayName("SUBSISTENCIA_ADMINISTRADORS")
    void subsitenciaAdministradors() throws ClientException {
        RespostaConsultaSubsistenciaAdministradors resposta = this.send(OperacioNotaris.SUBSISTENCIA_ADMINISTRADORS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}