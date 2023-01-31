package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.utils.PeticionBuilderImpl;
import generated.tfn.PeticioDadesCompletes;
import generated.tfn.TTipusDocumentacio;

public class TFNPeticionBuilder extends PeticionBuilderImpl {
    private static final String DOCUMENTACIO = "client.tfn.documentacio";

    public TFNPeticionBuilder(String propertiesPath) throws NotFoundException {
        super(propertiesPath);
    }

    @Override
    public Object[] getDatosEspecificos(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((TFNOperacio) operacio) {
                case TFN_DADESCOMPLETES -> new Object[]{
                        buildPeticioDadesCompletes()
                };
                case TFN_DADESCOMPLETES_DIS, TFN_VIGENCIA -> new Object[]{};
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    private PeticioDadesCompletes buildPeticioDadesCompletes(){
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular(){
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio(properties.getProperty(DOCUMENTACIO));
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
