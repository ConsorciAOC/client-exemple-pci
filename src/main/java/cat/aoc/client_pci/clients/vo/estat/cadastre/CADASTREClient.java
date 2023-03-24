package cat.aoc.client_pci.clients.vo.estat.cadastre;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class CADASTREClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.cadastre.certificacio",
            //"generated.cadastre.dades",
            //"generated.cadastre.grafica",
    };

    public CADASTREClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, CADASTREOperacio.class);
        return switch ((CADASTREOperacio) operacio) {
            default -> Frontal.SINCRON;
        };
    }

}
