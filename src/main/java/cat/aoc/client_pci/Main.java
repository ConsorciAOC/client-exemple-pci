package cat.aoc.client_pci;

import cat.aoc.client_pci.soap.Clients;
import cat.aoc.tfn.PeticioDadesCompletes;
import cat.aoc.tfn.TTipusDocumentacio;
import net.gencat.scsp.esquemes.peticion.Peticion;

public class Main {
    public static void main(String[] args) {
        PeticionBuilder builder = new PeticionBuilder();
        Peticion peticion = builder.build(
                "TFN",
                "TFN_DADESCOMPLETES",
                "PROVES",
                buildPeticioDadesCompletes()
        );
        try {
             Clients.TFN.getClient(Entorn.PRE).send(peticion);
        } catch (Exception e) {
            e.printStackTrace();
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
