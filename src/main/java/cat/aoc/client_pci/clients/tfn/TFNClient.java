package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;

public class TFNClient extends ClientAOC<TFNOperacio> {
    private static final String[] PACKAGES = {
            "generated.tfn"
    };

    public TFNClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(TFNOperacio operacio) {
        return switch (operacio) {
            case TFN_VIGENCIA, TFN_DADESCOMPLETES, TFN_DADESCOMPLETES_DIS -> Frontal.SINCRON;
        };
    }

}
