package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;

public class ENOTUMClient extends ClientAOC<ENOTUMOperacio> {
    private static final String[] PACKAGES = {
            "generated.enotum",
    };

    public ENOTUMClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.NT, PACKAGES);
    }

    @Override
    public Frontal getFrontal(ENOTUMOperacio operacio) {
        return switch (operacio) {
            case PROCESSAR_TRAMESA, RESUM, EVIDENCIA, PRACTICAR, RECUPERAR_REPORT, CONSULTA, PARAULA_PAS, CERCA -> Frontal.SINCRON;
        };
    }

}
