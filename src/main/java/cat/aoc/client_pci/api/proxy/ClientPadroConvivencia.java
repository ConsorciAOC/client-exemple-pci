package cat.aoc.client_pci.api.proxy;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;

import java.util.Properties;

public class ClientPadroConvivencia extends ClientPCI {
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.convivencia",
    };

    public ClientPadroConvivencia(Entorn entorn, Frontal frontal, Properties properties) {
        super(entorn, Cluster.IOP, frontal, PACKAGES, properties);
    }

}
