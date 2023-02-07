package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class ENOTUMClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.enotum",
    };

    public ENOTUMClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.NT, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, ENOTUMOperacio.class);
        return switch ((ENOTUMOperacio) operacio) {
            case PROCESSAR_TRAMESA,
                    RESUM,
                    EVIDENCIA,
                    PRACTICAR,
                    RECUPERAR_REPORT,
                    CONSULTA,
                    PARAULA_PAS,
                    CERCA -> Frontal.SINCRON;
        };
    }

}
