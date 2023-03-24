package cat.aoc.client_pci.clients.vo.estat.estrangeria;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.estrangeria.PeticioConsultaDadesResidenciaLegal;

public class ESTRANGERIAPeticionBuilder extends AbstractPeticionBuilder<ESTRANGERIAOperacio> {
    public ESTRANGERIAPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(ESTRANGERIAOperacio operacio) {
        return switch (operacio) {
            case RESIDENCIA_LEGAL -> new Object[]{
                    buildPeticioConsultaDadesResidenciaLegal()
            };
        };
    }

    private PeticioConsultaDadesResidenciaLegal buildPeticioConsultaDadesResidenciaLegal() {
        PeticioConsultaDadesResidenciaLegal peticio = new PeticioConsultaDadesResidenciaLegal();
        peticio.setAnyNaixement("1978");
        return peticio;
    }

}
