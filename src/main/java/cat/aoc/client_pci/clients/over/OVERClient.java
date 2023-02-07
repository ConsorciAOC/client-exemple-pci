package cat.aoc.client_pci.clients.over;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;

public class OVERClient extends ClientAOC<OVEROperacio> {
    private static final String[] PACKAGES = {
            "generated.over",
    };

    public OVERClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.APP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(OVEROperacio operacio) {
        return switch (operacio) {
            case OVER_DOCUMENTACIO,
                    OVER_FORMULARI,
                    OVER_CONTEXT,
                    OVER_TRAMITACIO,
                    OVER_ACTUALITZACIO,
                    OVER_CONSULTA,
                    OVER_LLISTA_EXPEDIENTS,
                    OVER_CONSULTA_EXPEDIENT,
                    OVER_LLISTA_SERVEIS,
                    OVER_LLISTA_TRAMITS,
                    OVER_INTEGRACIO -> Frontal.SINCRON;
        };
    }

}
