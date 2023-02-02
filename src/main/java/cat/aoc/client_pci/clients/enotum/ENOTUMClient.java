package cat.aoc.client_pci.clients.enotum;

import cat.aoc.client_pci.clients.ClientAOC;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;

public class ENOTUMClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.enotum",
    };

    public ENOTUMClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.NT, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((ENOTUMOperacio) operacio) {
                case PROCESSAR_TRAMESA, RESUM, EVIDENCIA, PRACTICAR, RECUPERAR_REPORT, CONSULTA, PARAULA_PAS, CERCA -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "ENOTUM";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return "ENOTUM";
    }

}
