package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.clients.ClientAOC;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.*;

public class TFNClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.tfn"
    };

    public TFNClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws NotDefinedException {
        try {
            return switch ((TFNOperacio) operacio) {
                case TFN_VIGENCIA, TFN_DADESCOMPLETES, TFN_DADESCOMPLETES_DIS -> Frontal.SINCRON;
            };
        } catch (Exception e) {
            throw new NotDefinedException("Modalitat no definida: " + operacio);
        }
    }

    @Override
    public String getCodiServei() {
        return "TFN";
    }

    @Override
    public String getCodiModalitat(Operacio operacio) {
        return ((TFNOperacio) operacio).name();
    }

}
