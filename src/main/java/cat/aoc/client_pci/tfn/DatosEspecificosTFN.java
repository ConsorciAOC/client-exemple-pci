package cat.aoc.client_pci.tfn;

import cat.aoc.client_pci.DatosEspecificos;
import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.Serializable;

public class DatosEspecificosTFN extends DatosEspecificos {

    private PeticioDadesCompletes peticioDadesCompletes;

    public PeticioDadesCompletes getPeticioDadesCompletes() {
        return peticioDadesCompletes;
    }

    public void setPeticioDadesCompletes(PeticioDadesCompletes peticioDadesCompletes) {
        this.peticioDadesCompletes = peticioDadesCompletes;
    }
}