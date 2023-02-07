package cat.aoc.client_pci.clients.dgp;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;

public class DGPClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.dgp",
    };

    public DGPClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }
    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((DGPOperacio) operacio) {
                case IDENTITAT_DADES, IDENTITAT_VERIFICACIO -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "DGP_IDENTITAT";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return ((DGPOperacio) operacio).name();
    }

}
