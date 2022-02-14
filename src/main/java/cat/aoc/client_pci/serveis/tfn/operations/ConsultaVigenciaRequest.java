package cat.aoc.client_pci.serveis.tfn.operations;

import cat.aoc.client_pci.Cluster;
import cat.aoc.client_pci.Entorn;
import cat.aoc.client_pci.Frontal;
import cat.aoc.client_pci.PeticionBuilder;
import cat.aoc.client_pci.operations.SOAPRequestSiri;
import cat.aoc.tfn.PeticioConsultaVigencia;
import cat.aoc.tfn.RespostaConsultaVigencia;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

public class ConsultaVigenciaRequest extends SOAPRequestSiri<PeticioConsultaVigencia, RespostaConsultaVigencia> {

    public ConsultaVigenciaRequest(Entorn entorn) throws Exception {
        super(entorn, Cluster.IOP, Frontal.SINCRON, "cat.aoc.tfn");
    }

    @Override
    public RespostaConsultaVigencia invoke(PeticioConsultaVigencia vigencia) {
        Peticion peticion = buildPeticion(vigencia);
        Respuesta respuesta = peticion(peticion);
        System.out.println(respuesta);
        return (RespostaConsultaVigencia) respuesta.getTransmisiones().getTransmisionDatos().get(0).getDatosEspecificos().getAny().get(0);
    }

    protected Peticion buildPeticion(PeticioConsultaVigencia vigencia){
        PeticionBuilder builder = new PeticionBuilder();
        return builder.build(
                "TFN",
                "TFN_DADESCOMPLETES",
                "PROVES",
                vigencia
        );
    }

}
