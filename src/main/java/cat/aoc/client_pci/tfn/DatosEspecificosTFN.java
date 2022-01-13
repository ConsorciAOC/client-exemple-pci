package cat.aoc.client_pci.tfn;

import cat.aoc.client_pci.DatosEspecificos;
import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;

public class DatosEspecificosTFN extends DatosEspecificos {

    private PeticioDadesCompletes peticioDadesCompletes;

    public PeticioDadesCompletes getPeticioDadesCompletes() {
        return peticioDadesCompletes;
    }

    public void setPeticioDadesCompletes(PeticioDadesCompletes peticioDadesCompletes) {
        this.peticioDadesCompletes = peticioDadesCompletes;
    }
}