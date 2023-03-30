package cat.aoc.client_pci.samples.serveis.representa;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.representa.PeticionBuilderRepresentaConsultarRepresentacio.buildConsultarRepresentacio;

public class PeticionBuilderRepresenta extends PeticionBuilderFromProperties<OperacioRepresenta> {

    public PeticionBuilderRepresenta(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRepresenta operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioRepresenta operacio) {
        return switch (operacio) {
            case CONSULTA_REPRESENTACIO -> buildConsultarRepresentacio();
            case CONSULTA_REPRESENTACIONS, VALIDACIO,
                    ALTA,
                    MODIFICACIO,
                    CONSULTA_CATALEG,
                    CONSULTA_FAMILIES,
                    CONSULTA_FAMILIA,
                    CONSULTA_TRAMITS,
                    CONSULTA_ADMINISTRACIO,
                    CONSULTA_ADMINISTRACIONS,
                    DESCARREGA -> new Object();
        };
    }

}
