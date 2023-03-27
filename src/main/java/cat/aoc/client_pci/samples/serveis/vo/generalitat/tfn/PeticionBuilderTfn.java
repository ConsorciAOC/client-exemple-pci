package cat.aoc.client_pci.samples.serveis.vo.generalitat.tfn;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.tfn.PeticioDadesCompletes;
import generated.tfn.TTipusDocumentacio;

import java.util.Properties;

public class PeticionBuilderTfn extends PeticionBuilderFromProperties<OperacioTfn> {
    public PeticionBuilderTfn(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioTfn operacio) {
        return switch (operacio) {
            case TFN_DADESCOMPLETES -> new Object[]{
                    buildPeticioDadesCompletes()
            };
            case TFN_DADESCOMPLETES_DIS, TFN_VIGENCIA -> new Object[]{};
        };
    }

    private PeticioDadesCompletes buildPeticioDadesCompletes() {
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular() {
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
