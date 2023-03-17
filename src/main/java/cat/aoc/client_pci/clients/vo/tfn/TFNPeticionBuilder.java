package cat.aoc.client_pci.clients.vo.tfn;

import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.utils.AbstractPeticionBuilder;
import generated.tfn.PeticioDadesCompletes;
import generated.tfn.TTipusDocumentacio;

public class TFNPeticionBuilder extends AbstractPeticionBuilder<TFNOperacio> {
    private static final String DOCUMENTACIO = "client.tfn.documentacio";

    public TFNPeticionBuilder(String propertiesPath) throws ClientException {
        super(propertiesPath);
    }

    @Override
    protected Object[] getDatosEspecificos(TFNOperacio operacio) {
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
        identificadorTitular.setDocumentacio(builder.getProperties().getProperty(DOCUMENTACIO));
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
