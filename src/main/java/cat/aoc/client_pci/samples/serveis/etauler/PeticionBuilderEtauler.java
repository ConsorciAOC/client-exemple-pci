package cat.aoc.client_pci.samples.serveis.etauler;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.etauler.PeticionBuilderEtaulerConsultar.buildPeticioConsultarEstatEdicte;

public class PeticionBuilderEtauler extends PeticionBuilderFromProperties<OperacioEtauler> {
    public PeticionBuilderEtauler(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEtauler operacio) {
        return new Object[]{ getDatoEspecifico(operacio) };
    }

    private Object getDatoEspecifico(OperacioEtauler operacio) {
        return switch (operacio) {
            case CONSULTAR -> buildPeticioConsultarEstatEdicte();
            case PUBLICAR, DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT ->
                    null;
        };
    }

}
