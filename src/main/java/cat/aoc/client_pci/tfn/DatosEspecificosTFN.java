package cat.aoc.client_pci.tfn;

import cat.aoc.client_pci.jaxb.tfn.PeticioDadesCompletes;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "peticioDadesCompletes",
})
@XmlRootElement(name = "datosEspecificos")
public class DatosEspecificosTFN {

    protected PeticioDadesCompletes peticioDadesCompletes;

    public PeticioDadesCompletes getPeticioDadesCompletes() {
        return peticioDadesCompletes;
    }

    public void setPeticioDadesCompletes(PeticioDadesCompletes peticioDadesCompletes) {
        this.peticioDadesCompletes = peticioDadesCompletes;
    }

}
