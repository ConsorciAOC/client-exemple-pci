package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.model.Finalitat;
import net.gencat.scsp.esquemes.peticion.Peticion;

public interface PeticionBuilder<O extends Operacio> {

    Peticion build(O operacio, Finalitat finalitat);

}
