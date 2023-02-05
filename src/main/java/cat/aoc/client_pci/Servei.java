package cat.aoc.client_pci;

import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.enotum.ENOTUMOperacio;
import cat.aoc.client_pci.clients.enotum.ENOTUMPeticionBuilder;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.etauler.ETAULEROperacio;
import cat.aoc.client_pci.clients.etauler.ETAULERPeticionBuilder;
import cat.aoc.client_pci.clients.padro.PADROOperacio;
import cat.aoc.client_pci.clients.padro.PADROPeticionBuilder;
import cat.aoc.client_pci.clients.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.clients.tfn.TFNOperacio;
import cat.aoc.client_pci.clients.tfn.TFNPeticionBuilder;
import cat.aoc.client_pci.model.exceptions.NotDefinedException;
import cat.aoc.client_pci.model.exceptions.NotFoundException;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import lombok.Getter;
import net.gencat.scsp.esquemes.peticion.Peticion;

@Getter
public enum Servei {
    ENOTUM(Cluster.NT, "ENOTUM"),
    ETAULER(Cluster.APP, "ETAULER"),
    MUX(Cluster.IOP, "MUX"),
    TFN (Cluster.IOP, "TFN"),
    PADRO(Cluster.IOP, "PADRO");

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private final Cluster cluster;
    private final String codi;

    Servei(Cluster cluster, String codi) {
        this.cluster = cluster;
        this.codi = codi;
    }

    public ClientAOC getClient(Entorn entorn) throws NotDefinedException {
        return switch (this) {
            case TFN -> new TFNClient(KEYSTORE_PATH, entorn);
            case PADRO -> new PADROProxyClient(KEYSTORE_PATH, entorn);
            case ENOTUM -> new ENOTUMClient(KEYSTORE_PATH, entorn);
            case ETAULER -> new ETAULERClient(KEYSTORE_PATH, entorn);
            default -> throw new NotDefinedException("Servei no definit: " + this.name());
        };
    }

    public Peticion getPeticion(Operacio operacio, Finalitat finalidad) throws NotDefinedException, NotFoundException {
        return switch (this) {
            case ENOTUM -> new ENOTUMPeticionBuilder(PROPERTIES_PATH).build((ENOTUMOperacio) operacio, finalidad);
            case ETAULER -> new ETAULERPeticionBuilder(PROPERTIES_PATH).build((ETAULEROperacio) operacio, finalidad);
            case PADRO -> new PADROPeticionBuilder(PROPERTIES_PATH).build((PADROOperacio) operacio, finalidad);
            case TFN -> new TFNPeticionBuilder(PROPERTIES_PATH).build((TFNOperacio) operacio, finalidad);
            default -> throw new NotDefinedException("Servei no definit: " + this.name());
        };
    }

}
