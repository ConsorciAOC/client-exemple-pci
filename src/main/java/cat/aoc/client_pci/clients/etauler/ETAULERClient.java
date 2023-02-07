package cat.aoc.client_pci.clients.etauler;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;

public class ETAULERClient extends ClientAOC<ETAULEROperacio> {
    private static final String[] PACKAGES = {
            "generated.etauler"
    };

    public ETAULERClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.APP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(ETAULEROperacio operacio) {
        return switch (operacio) {
            case PUBLICAR, DADES, AMPLIAR_TERMINI, CANCELAR, DESPENJAR, SINCRONITZAR, DESCARREGAR_DOCUMENT, CONSULTAR -> Frontal.SINCRON;
        };
    }

}
