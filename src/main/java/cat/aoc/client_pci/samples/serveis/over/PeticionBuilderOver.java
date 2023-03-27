package cat.aoc.client_pci.samples.serveis.over;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.over.PeticionBuilderOverDocumentacio.buildPeticioDocumentacioTramit;

public class PeticionBuilderOver extends PeticionBuilderFromProperties<OperacioOver> {

    public PeticionBuilderOver(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioOver operacio) {
        return new Object[]{ getDatoEspecifico(operacio) };
    }

    private Object getDatoEspecifico(OperacioOver operacio) {
        return switch (operacio) {
            case OVER_DOCUMENTACIO -> buildPeticioDocumentacioTramit();
            case OVER_FORMULARI,
                    OVER_CONTEXT,
                    OVER_TRAMITACIO,
                    OVER_ACTUALITZACIO,
                    OVER_CONSULTA,
                    OVER_LLISTA_EXPEDIENTS,
                    OVER_CONSULTA_EXPEDIENT,
                    OVER_LLISTA_SERVEIS,
                    OVER_LLISTA_TRAMITS,
                    OVER_INTEGRACIO -> new Object();
        };
    }

}
