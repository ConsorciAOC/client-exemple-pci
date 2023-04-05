package cat.aoc.client_pci.api.clients.proxy;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import generated.pci.peticion.Peticion;
import generated.pci.respuesta.Respuesta;

import java.util.Properties;

public class ProxyClientPadro extends ClientPCI {
    private static final String[] EMPADRONAMIENTO_PACKAGES = {
            "generated.serveis.padro",
            "generated.serveis.padro.empadronamiento",
    };

    private static final String[] CONVIVENVIA_PACKAGES = {
            "generated.serveis.padro",
            "generated.serveis.padro.convivencia",
    };

    private final ClientPCI clientEmpadronamiento;
    private final ClientPCI clientConvivencia;

    public ProxyClientPadro(Entorn entorn, Frontal frontal, Properties properties) {
        super(entorn, Cluster.IOP, frontal, new String[]{}, properties);
        this.clientEmpadronamiento = new ClientPCI(entorn, Cluster.IOP, frontal, EMPADRONAMIENTO_PACKAGES, properties);
        this.clientConvivencia = new ClientPCI(entorn, Cluster.IOP, frontal, CONVIVENVIA_PACKAGES, properties);
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
