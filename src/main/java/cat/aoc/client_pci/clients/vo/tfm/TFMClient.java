package cat.aoc.client_pci.clients.vo.tfm;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Frontal;
import cat.aoc.client_pci.model.Operacio;
import cat.aoc.client_pci.model.exceptions.ClientException;

public class TFMClient extends ClientAOC {
    private static final String[] PACKAGES = {
            "generated.tfm"
    };

    public TFMClient(String keystorePath, Entorn entorn) {
        super(keystorePath, entorn, Cluster.IOP, PACKAGES);
    }

    @Override
    public Frontal getFrontal(Operacio operacio) throws ClientException {
        checkOperacio(operacio, TFMOperacio.class);
        return switch ((TFMOperacio) operacio) {
            case TFM_VIGENCIA, TFM_DADESCOMPLETES, TFM_DADESCOMPLETES_DIS, TFM_DADESCOMPLETES_MASSIU, TFM_DADESCOMPLETES_DIS_MASSIU -> Frontal.SINCRON;
        };
    }

}
