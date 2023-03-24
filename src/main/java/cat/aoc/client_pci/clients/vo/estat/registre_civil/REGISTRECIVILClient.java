package cat.aoc.client_pci.clients.vo.estat.registre_civil;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class REGISTRECIVILClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.registre_civil",
    };

    public REGISTRECIVILClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, REGISTRECIVILOperacio.class);
        return switch ((REGISTRECIVILOperacio) operacio) {
            default -> Frontal.SINCRON;
        };
    }

}
