package cat.aoc.client_pci.samples.serveis.vo.generalitat.registre_entitats;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.registre_entitats.PeticioBasicaConsulta;

import java.util.Properties;

public class PeticionBuilderRegistreEntitats extends PeticionBuilderFromProperties<OperacioRegistreEntitats> {
    public PeticionBuilderRegistreEntitats(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRegistreEntitats operacio) {
        return switch (operacio) {
            case ENTITAT_INSCRIPCIO -> new Object[]{
                    buildPeticioBasicaConsulta()
            };
            case ENTITAT_DADES,
                    ENTITAT_ESTATUTS,
                    ENTITAT_ESCRIPTURES,
                    ENTITAT_COMPTES -> new Object[]{};
        };
    }

    private PeticioBasicaConsulta buildPeticioBasicaConsulta() {
        PeticioBasicaConsulta peticio = new PeticioBasicaConsulta();
        peticio.setNomEntitat("Xarxes");
        return peticio;
    }

}
