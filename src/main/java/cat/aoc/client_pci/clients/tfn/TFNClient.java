package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.WebServiceSupportException;
import cat.aoc.client_pci.model.*;
import net.gencat.scsp.esquemes.peticion.Peticion;
import cat.aoc.tfn.PeticioDadesCompletes;
import cat.aoc.tfn.TTipusDocumentacio;

public class TFNClient extends ClientAOC {
    private static final String CODI_SERVEI = "TFN";
    private static final String[] PACKAGES = {
            "cat.aoc.tfn"
    };

    private final PeticionBuilder peticionBuilder;
    public TFNClient(Entorn entorn, PeticionBuilder peticionBuilder) throws WebServiceSupportException {
        super(entorn, Cluster.IOP, PACKAGES);
        this.peticionBuilder = peticionBuilder;
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        switch ((TFNOperacio) operacio){
            case TFN_VIGENCIA:
            case TFN_DADESCOMPLETES:
            case TFN_DADESCOMPLETES_DIS:
                return Frontal.SINCRON;
            default:
                throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    @Override
    public Peticion getPeticion(Operacio operacio, Finalitat finalitat) {
        return peticionBuilder.build(
                CODI_SERVEI,
                ((TFNOperacio) operacio).name(),
                finalitat.name(),
                getDatosEspecificos(operacio)
        );
    }

    private Object[] getDatosEspecificos(Operacio operacio) {
        switch ((TFNOperacio) operacio){
            case TFN_DADESCOMPLETES:
                return new Object[] {
                        buildPeticioDadesCompletes()
                };
            case TFN_DADESCOMPLETES_DIS:
            case TFN_VIGENCIA:
            default:
                return new Object[]{};
        }
    }

    private static PeticioDadesCompletes buildPeticioDadesCompletes(){
        PeticioDadesCompletes peticioDadesCompletes = new PeticioDadesCompletes();
        peticioDadesCompletes.setIdentificadorTitular(buildIdentificadorTitular());
        return peticioDadesCompletes;
    }

    private static PeticioDadesCompletes.IdentificadorTitular buildIdentificadorTitular(){
        PeticioDadesCompletes.IdentificadorTitular identificadorTitular = new PeticioDadesCompletes.IdentificadorTitular();
        identificadorTitular.setDocumentacio("38991311D");
        identificadorTitular.setTipusDocumentacio(TTipusDocumentacio.NIF);
        return identificadorTitular;
    }

}
