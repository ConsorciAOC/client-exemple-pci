package cat.aoc.client_pci;

import net.gencat.scsp.esquemes.peticion.Peticion;

public interface PeticionBuilder {

    Peticion build(String producto, String modalidad, String finalidad, Object... dadesEspecifiques);

}
