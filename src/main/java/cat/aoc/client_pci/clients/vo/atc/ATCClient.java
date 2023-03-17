package cat.aoc.client_pci.clients.vo.atc;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class ATCClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.atc",
    };

    public ATCClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, ATCOperacio.class);
        return switch ((ATCOperacio) operacio) {
            case ATC_INF_DEUTES_TMP -> Frontal.SINCRON;
        };
    }

}
