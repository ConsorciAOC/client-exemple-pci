package cat.aoc.client_pci.clients.vo.estat.dependencia;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class DEPENDENCIAClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.dependencia",
    };

    public DEPENDENCIAClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, DEPENDENCIAOperacio.class);
        return switch ((DEPENDENCIAOperacio) operacio) {
            default -> Frontal.SINCRON;
        };
    }

}
