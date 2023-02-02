package cat.aoc.client_pci.utils;

import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import net.gencat.scsp.esquemes.peticion.Peticion;

public interface PeticionBuilder {

    Peticion build(String producte, Operacio operacio, Finalitat finalitat) throws NotDefinedException, NotFoundException;

}
