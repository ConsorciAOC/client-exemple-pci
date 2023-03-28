package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.registre_civil.OperacioRegistreCivil;
import cat.aoc.client_pci.samples.serveis.vo.estat.registre_civil.PeticionBuilderRegistreCivil;
import generated.registre_civil.RespostaConsultaRegistreCivil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RegistreCivilTest extends AbstractClientPCITest<OperacioRegistreCivil> {

    public RegistreCivilTest() throws IOException {
        super(Serveis.REGISTRE_CIVIL.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderRegistreCivil(PROPERTIES));
    }

    @Test
    @DisplayName("NAIXEMENT")
    void naixement() throws ClientException {
        RespostaConsultaRegistreCivil resposta = this.send(OperacioRegistreCivil.NAIXEMENT, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}