package cat.aoc.client_pci.samples.serveis.vo.estat.dependencia;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

public class PeticionBuilderDependencia extends PeticionBuilderFromProperties<OperacioDependencia> {
    public PeticionBuilderDependencia(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioDependencia operacio) {
        return switch (operacio) {
            case GRAU_DEPENDENCIA -> new Object[]{};
        };
    }

}
