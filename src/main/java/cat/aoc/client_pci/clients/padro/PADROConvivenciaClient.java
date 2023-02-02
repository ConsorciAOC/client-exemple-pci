package cat.aoc.client_pci.clients.padro;

import cat.aoc.client_pci.clients.ClientAOC;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;

public class PADROConvivenciaClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.convivencia",
    };

    public PADROConvivenciaClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((PADROOperacio) operacio) {
                case RESIDENT, MUNICIPI_RESIDENCIA, RESIDENT_MUNICIPI, NUMERO_CONVIVENTS, COMPROVACIO_CONVIVENTS, TITULAR, TITULAR_PROPI, CONVIVENTS, CONVIVENTS_PROPI, TITULAR_PDF, CONVIVENTS_PDF, TITULAR_IDESCAT, CERCA_TITULAR -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "PADRO";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return ((PADROOperacio) operacio).name();
    }

}
