package cat.aoc.client_pci.api.clients.proxy;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import net.gencat.scsp.esquemes.peticion.Peticion;
import net.gencat.scsp.esquemes.respuesta.Respuesta;

import java.util.Properties;

public class ProxyClientPadro extends ClientPCI {

    private final ClientPadroEmpadronamiento clientEmpadronamiento;
    private final ClientPadroConvivencia clientConvivencia;

    public ProxyClientPadro(Entorn entorn, Frontal frontal, Properties properties) {
        super(entorn, Cluster.IOP, frontal, new String[]{}, properties);
        this.clientEmpadronamiento = new ClientPadroEmpadronamiento(entorn, frontal, properties);
        this.clientConvivencia = new ClientPadroConvivencia(entorn, frontal, properties);
    }

    @Override
    public Respuesta send(Peticion peticion) throws ClientException {
        String modalitat = peticion.getAtributos().getCodigoCertificado();
        return switch (modalitat) {
            case "TITULAR",
                    "TITULAR_IDESCAT",
                    "CERCA_TITULAR",
                    "TITULAR_PROPI",
                    "TITULAR_PDF",
                    "RESIDENT",
                    "MUNICIPI_RESIDENCIA",
                    "RESIDENT_MUNICIPI" -> clientEmpadronamiento.send(peticion);
            case "CONVIVENTS",
                    "NUMERO_CONVIVENTS",
                    "CONVIVENTS_PROPI",
                    "CONVIVENTS_PDF",
                    "COMPROVACIO_CONVIVENTS" -> clientConvivencia.send(peticion);
            default -> throw new ClientException("Modalitat no definida: " + modalitat);
        };
    }
}
