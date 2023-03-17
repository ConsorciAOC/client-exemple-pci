package cat.aoc.client_pci.clients.vo.registre_entitats;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class REClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.registre_entitats"
    };

    public REClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, REOperacio.class);
        return Frontal.SINCRON;
    }

}
