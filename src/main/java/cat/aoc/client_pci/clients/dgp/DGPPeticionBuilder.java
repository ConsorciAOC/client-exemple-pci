package cat.aoc.client_pci.clients.dgp;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.dgp.PeticioConsultaDadesIdentitat;
import generated.dgp.PeticioVerificacioDadesIdentitat;

public class DGPPeticionBuilder extends AbstractPeticionBuilder<DGPOperacio> {
    public DGPPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(DGPOperacio operacio) {
        return switch (operacio) {
            case IDENTITAT_DADES -> new Object[]{
                    buildPeticioConsultaDadesIdentitat()
            };
            case IDENTITAT_VERIFICACIO -> new Object[]{
                    buildPeticioVerificacioDadesIdentitat()
            };
        };
    }

    private PeticioConsultaDadesIdentitat buildPeticioConsultaDadesIdentitat() {
        return new PeticioConsultaDadesIdentitat();
    }

    private PeticioVerificacioDadesIdentitat buildPeticioVerificacioDadesIdentitat() {
        return new PeticioVerificacioDadesIdentitat();
    }
}
