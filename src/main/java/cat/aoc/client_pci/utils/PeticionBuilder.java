package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import net.gencat.scsp.esquemes.peticion.Peticion;

public interface PeticionBuilder {

    Peticion build(Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException;

}
