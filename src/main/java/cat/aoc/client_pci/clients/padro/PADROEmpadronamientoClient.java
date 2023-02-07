package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.*;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class PADROEmpadronamientoClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.empadronamiento",
    };

    public PADROEmpadronamientoClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, PADROOperacio.class);
        return switch ((PADROOperacio) operacio) {
            case RESIDENT,
                    MUNICIPI_RESIDENCIA,
                    RESIDENT_MUNICIPI,
                    NUMERO_CONVIVENTS,
                    COMPROVACIO_CONVIVENTS,
                    TITULAR,
                    TITULAR_PROPI,
                    CONVIVENTS,
                    CONVIVENTS_PROPI,
                    TITULAR_PDF,
                    CONVIVENTS_PDF,
                    TITULAR_IDESCAT,
                    CERCA_TITULAR -> Frontal.SINCRON;
        };
    }

}
