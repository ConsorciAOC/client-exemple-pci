package cat.aoc.client_pci.samples.serveis.vo.generalitat.soc;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

public class PeticionBuilderSoc extends PeticionBuilderFromProperties<OperacioSoc> {
    public PeticionBuilderSoc(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioSoc operacio) {
        return switch (operacio) {
            case SOC_CERT_INSCRIPCIO,
                    SOC_CERT_ULTIMPERIODE,
                    SOC_CERT_DADESPERSONALS,
                    SOC_CERT_DONO -> new Object[]{};
        };
    }

}
