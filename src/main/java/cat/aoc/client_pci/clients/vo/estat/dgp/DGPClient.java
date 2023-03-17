package cat.aoc.client_pci.clients.vo.estat.dgp;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class DGPClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.dgp",
    };

    public DGPClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, DGPOperacio.class);
        return switch ((DGPOperacio) operacio) {
            case IDENTITAT_DADES,
                    IDENTITAT_VERIFICACIO -> Frontal.SINCRON;
        };
    }

}
