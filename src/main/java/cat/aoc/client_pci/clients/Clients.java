package cat.aoc.client_pci.clients;

import cat.aoc.client_pci.ClientAOC;
import cat.aoc.client_pci.clients.enotum.ENOTUMPeticionBuilder;
import cat.aoc.client_pci.clients.etauler.ETAULERPeticionBuilder;
import cat.aoc.client_pci.clients.padro.PADROPeticionBuilder;
import cat.aoc.client_pci.clients.tfn.TFNPeticionBuilder;
import cat.aoc.client_pci.exceptions.NotDefinedException;
import cat.aoc.client_pci.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;

public enum Clients {
    TFN,
    PADRO,
    ENOTUM,
    ETAULER,
    MUX;

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    public ClientAOC getClient(Entorn entorn) throws NotDefinedException, NotFoundException {
        return switch (this) {
            case TFN -> new TFNClient(KEYSTORE_PATH, entorn, new TFNPeticionBuilder(PROPERTIES_PATH));
            case PADRO -> new PADROProxyClient(KEYSTORE_PATH, entorn, new PADROPeticionBuilder(PROPERTIES_PATH));
            case ENOTUM -> new ENOTUMClient(KEYSTORE_PATH, entorn, new ENOTUMPeticionBuilder(PROPERTIES_PATH));
            case ETAULER -> new ETAULERClient(KEYSTORE_PATH, entorn, new ETAULERPeticionBuilder(PROPERTIES_PATH));
            default -> throw new NotDefinedException("Servei no definit: " + this.name());
        };
    }

}
