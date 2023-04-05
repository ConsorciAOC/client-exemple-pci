package cat.aoc.client_pci.samples.serveis.vo.generalitat.rca;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.rca.PeticioVerificacioAssegurat;

import java.util.Properties;

public class PeticionBuilderRca extends PeticionBuilderFromProperties<OperacioRca> {
    public PeticionBuilderRca(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRca operacio) {
        return switch (operacio) {
            case RCA_VERIFICACIO -> new Object[]{
                    buildPeticioVerificacioAssegurat()
            };
            case RCA_CONSULTA -> new Object[]{};
        };
    }

    private PeticioVerificacioAssegurat buildPeticioVerificacioAssegurat() {
        PeticioVerificacioAssegurat peticioVerificacioAssegurat = new PeticioVerificacioAssegurat();
        peticioVerificacioAssegurat.setCIP("GURI0760720000");
        peticioVerificacioAssegurat.setPrimerCognom("gissela");
        return peticioVerificacioAssegurat;
    }

}
