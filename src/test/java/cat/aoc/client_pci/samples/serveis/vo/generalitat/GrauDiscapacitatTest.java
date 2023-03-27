package cat.aoc.client_pci.samples.serveis.vo.generalitat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.grau_discapacitat.OperacioGrauDiscapacitat;
import cat.aoc.client_pci.samples.serveis.vo.generalitat.grau_discapacitat.PeticionBuilderGrauDiscapacitat;
import generated.grau_discapacitat.RespostaConsultaDiscapacitatSimple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GrauDiscapacitatTest extends AbstractClientPCITest<OperacioGrauDiscapacitat> {
    public GrauDiscapacitatTest() throws IOException {
        super(Serveis.GRAU_DISCAPACITAT.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderGrauDiscapacitat(PROPERTIES));
    }

    @Test
    @DisplayName("GRAU_DISCAPACITAT_SIMPLE")
    void grauDiscapacitatSimple() throws ClientException {
        RespostaConsultaDiscapacitatSimple resposta = this.send(OperacioGrauDiscapacitat.GRAU_DISCAPACITAT_SIMPLE, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}
