package cat.aoc.client_pci.clients.dgp;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;

public class DGPClient extends ClientAOC<DGPOperacio> {
    private static final String[] PACKAGES = {
            "generated.dgp",
    };

    public DGPClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(DGPOperacio operacio) {
        return switch (operacio) {
            case IDENTITAT_DADES, IDENTITAT_VERIFICACIO -> Frontal.SINCRON;
        };
    }

}
