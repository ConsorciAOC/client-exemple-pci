package cat.aoc.client_pci.samples.serveis.eacat;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.eacat.PeticionBuilderEacatEns.buildPeticioConsultaEns;
import static cat.aoc.client_pci.samples.serveis.eacat.PeticionBuilderEacatServei.buildPeticioConsultaServei;
import static cat.aoc.client_pci.samples.serveis.eacat.PeticionBuilderEacatTipusEns.buildPeticioConsultaTipusEns;
import static cat.aoc.client_pci.samples.serveis.eacat.PeticionBuilderEacatUsuari.buildPeticioConsultaUsuari;

public class PeticionBuilderEacat extends PeticionBuilderFromProperties<OperacioEacat> {

    public PeticionBuilderEacat(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEacat operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioEacat operacio) {
        return switch (operacio) {
            case EACAT_USUARI -> buildPeticioConsultaUsuari();
            case EACAT_SERVEI -> buildPeticioConsultaServei();
            case EACAT_ENS -> buildPeticioConsultaEns();
            case EACAT_TIPUS_ENS -> buildPeticioConsultaTipusEns();
        };
    }

}
