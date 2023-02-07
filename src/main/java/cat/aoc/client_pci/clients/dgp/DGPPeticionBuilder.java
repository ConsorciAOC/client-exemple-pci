package cat.aoc.client_pci.clients.dgp;

import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.dgp.PeticioConsultaDadesIdentitat;

public class DGPPeticionBuilder extends AbstractPeticionBuilder<DGPOperacio> {
    public DGPPeticionBuilder(String propertiesPath) throws NotFoundException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(DGPOperacio operacio) {
        return switch (operacio) {
            case IDENTITAT_DADES -> new Object[]{
                    buildPeticioConsultaDadesIdentitat()
            };
            case IDENTITAT_VERIFICACIO -> new Object[]{};
        };
    }

    private PeticioConsultaDadesIdentitat buildPeticioConsultaDadesIdentitat() {
        return new PeticioConsultaDadesIdentitat();
    }

}