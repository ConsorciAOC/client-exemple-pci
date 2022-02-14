package cat.aoc.client_pci.serveis.padro.operations;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import cat.aoc.client_pci.operations.SOAPRequestSiri;
import cat.aoc.client_pci.PeticionBuilder;
import net.aocat.padro.PeticionDatosTitular;
import net.aocat.padro.RespuestaDatosTitular;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class PeticionDatosTitularRequest extends SOAPRequestSiri<PeticionDatosTitular, RespuestaDatosTitular> {

    public PeticionDatosTitularRequest(Entorn entorn) throws Exception {
        super(entorn, Cluster.IOP, Frontal.SINCRON, "net.aocat.padro");
    }

    @Override
    public RespuestaDatosTitular invoke(PeticionDatosTitular peticionDatosTitular) {
        Peticion peticion = buildPeticion(peticionDatosTitular);
        Respuesta respuesta = peticion(peticion);
        System.out.println(respuesta);
        return (RespuestaDatosTitular) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
    }

    protected Peticion buildPeticion(PeticionDatosTitular peticionDatosTitular){
        PeticionBuilder builder = new PeticionBuilder();
        return builder.build(
                "PADRO",
                "TITULAR",
                "PROVES",
                peticionDatosTitular
        );
    }

}
