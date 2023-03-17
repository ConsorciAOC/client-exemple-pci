package cat.aoc.client_pci.clients.vo.estat.dgt;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class DGTClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.dgt",
    };

    public DGTClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, DGTOperacio.class);
        return Frontal.SINCRON;
    }

}
