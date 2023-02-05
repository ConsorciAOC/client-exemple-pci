package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;

public class ETAULERClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.etauler"
    };

    public ETAULERClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.APP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((ETAULEROperacio) operacio) {
                case PUBLICAR, DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT, CONSULTAR -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Operacio no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "ETAULER";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return "ETAULER";
    }

}
