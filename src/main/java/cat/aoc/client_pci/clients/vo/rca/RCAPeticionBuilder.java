package cat.aoc.client_pci.clients.vo.rca;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.rca.PeticioVerificacioAssegurat;

public class RCAPeticionBuilder extends AbstractPeticionBuilder<RCAOperacio> {
    public RCAPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(RCAOperacio operacio) {
        return switch (operacio) {
            case RCA_VERIFICACIO -> new Object[]{
                    buildPeticioVerificacioAssegurat()
            };
            default -> new Object[]{};
        };
    }

    private PeticioVerificacioAssegurat buildPeticioVerificacioAssegurat() {
        PeticioVerificacioAssegurat peticioVerificacioAssegurat = new PeticioVerificacioAssegurat();
        peticioVerificacioAssegurat.setCIP("GURI0760720000");
        peticioVerificacioAssegurat.setPrimerCognom("gissela");
        return peticioVerificacioAssegurat;
    }

}
