package cat.aoc.client_pci.clients.vo.generalitat.tfn;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class TFNClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.tfn"
    };

    public TFNClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, TFNOperacio.class);
        return switch ((TFNOperacio) operacio) {
            case TFN_VIGENCIA, TFN_DADESCOMPLETES, TFN_DADESCOMPLETES_DIS -> Frontal.SINCRON;
        };
    }

}
