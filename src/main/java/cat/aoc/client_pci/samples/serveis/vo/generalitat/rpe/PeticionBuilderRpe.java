package cat.aoc.client_pci.samples.serveis.vo.generalitat.rpe;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

public class PeticionBuilderRpe extends PeticionBuilderFromProperties<OperacioRpe> {
    public PeticionBuilderRpe(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRpe operacio) {
        return switch (operacio) {
            case RPE_CONSULTA, RPE_VERIFICACIO -> new Object[]{};
        };
    }

}
