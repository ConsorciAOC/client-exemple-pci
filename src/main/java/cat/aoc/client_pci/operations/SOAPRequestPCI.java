package cat.aoc.client_pci.operations;

import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public interface SOAPRequestPCI extends SOAPRequest {

    Respuesta peticion(Peticion peticion);

}
