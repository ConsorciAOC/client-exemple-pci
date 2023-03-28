package cat.aoc.client_pci.api.clients.proxy;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;

import java.util.Properties;

public class ClientPadroEmpadronamiento extends ClientPCI {
    private static final String[] PACKAGES = {
            "generated.padro",
            "generated.padro.empadronamiento",
    };

    public ClientPadroEmpadronamiento(Entorn entorn, Frontal frontal, Properties properties) {
        super(entorn, Cluster.IOP, frontal, PACKAGES, properties);
    }

}
