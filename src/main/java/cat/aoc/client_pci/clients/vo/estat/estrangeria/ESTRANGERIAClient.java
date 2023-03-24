package cat.aoc.client_pci.clients.vo.estat.estrangeria;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class ESTRANGERIAClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.estrangeria",
    };

    public ESTRANGERIAClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, ESTRANGERIAOperacio.class);
        return switch ((ESTRANGERIAOperacio) operacio) {
            default -> Frontal.SINCRON;
        };
    }

}
