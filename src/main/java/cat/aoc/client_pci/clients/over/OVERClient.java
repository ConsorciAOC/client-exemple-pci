package cat.aoc.client_pci.clients.over;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;

public class OVERClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.over",
    };

    public OVERClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.APP, PACKAGES);
    }
    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        return Frontal.SINCRON;
    }

    @Override
    public String getCodiServei() {
        return "OVER";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return ((OVEROperacio) operacio).name();
    }

}
