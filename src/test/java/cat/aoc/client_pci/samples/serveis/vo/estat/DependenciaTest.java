package cat.aoc.client_pci.samples.serveis.vo.estat;

import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.api.Serveis;
import cat.aoc.client_pci.samples.serveis.vo.estat.dependencia.OperacioDependencia;
import cat.aoc.client_pci.samples.serveis.vo.estat.dependencia.PeticionBuilderDependencia;
import generated.dependencia.RespostaConsultaNivellGrauDependencia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DependenciaTest extends AbstractClientPCITest<OperacioDependencia> {

    public DependenciaTest() throws IOException {
        super(Serveis.DEPENDENCIA.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderDependencia(PROPERTIES));
    }

    @Test
    @DisplayName("GRAU_DEPENDENCIA")
    void grauDependencia() throws ClientException {
        RespostaConsultaNivellGrauDependencia resposta = this.send(OperacioDependencia.GRAU_DEPENDENCIA, Finalitat.PROVES);
        assertNotNull(resposta);
    }
    
}