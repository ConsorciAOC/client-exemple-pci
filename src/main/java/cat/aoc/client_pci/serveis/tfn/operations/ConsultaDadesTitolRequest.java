package cat.aoc.client_pci.serveis.tfn.operations;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import cat.aoc.client_pci.operations.SOAPRequestSiri;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.tfn.*;
import net.gencat.scsp.esquemes.peticion.*;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class ConsultaDadesTitolRequest extends SOAPRequestSiri<PeticioDadesCompletes, RespostaDadesCompletes> {

    public ConsultaDadesTitolRequest(Entorn entorn) throws Exception {
        super(entorn, Cluster.IOP, Frontal.SINCRON, "cat.aoc.tfn");
    }

    @Override
    public RespostaDadesCompletes invoke(PeticioDadesCompletes dadesCompletes) {
        Peticion peticion = buildPeticion(dadesCompletes);
        Respuesta respuesta = peticion(peticion);
        System.out.println(respuesta);
        return (RespostaDadesCompletes) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
    }

    protected Peticion buildPeticion(PeticioDadesCompletes dadesCompletes){
        PeticionBuilder builder = new PeticionBuilder();
        return builder.build(
                "TFN",
                "TFN_DADESCOMPLETES",
                "PROVES",
                dadesCompletes
        );
    }

}
