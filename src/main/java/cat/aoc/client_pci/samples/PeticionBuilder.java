package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.model.Finalitat;
import generated.pci.peticion.Peticion;

public interface PeticionBuilder<O extends Operacio> {

    Peticion build(O operacio, Finalitat finalitat);
    Peticion build(O operacio, String procedimiento);

}
