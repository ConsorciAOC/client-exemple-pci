package cat.aoc.client_pci.clients.vo.padro_historic;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class PADRO_HISTORICClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.padro_historic"
    };

    public PADRO_HISTORICClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, PADRO_HISTORICOperacio.class);
        return Frontal.SINCRON;
    }

}
