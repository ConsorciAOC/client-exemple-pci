package cat.aoc.client_pci;

import cat.aoc.client_pci.clients.dgp.DGPClient;
import cat.aoc.client_pci.clients.dgp.DGPOperacio;
import cat.aoc.client_pci.clients.dgp.DGPPeticionBuilder;
import cat.aoc.client_pci.clients.dgt.DGTClient;
import cat.aoc.client_pci.clients.dgt.DGTOperacio;
import cat.aoc.client_pci.clients.dgt.DGTPeticionBuilder;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.enotum.ENOTUMOperacio;
import cat.aoc.client_pci.clients.enotum.ENOTUMPeticionBuilder;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.etauler.ETAULEROperacio;
import cat.aoc.client_pci.clients.etauler.ETAULERPeticionBuilder;
import cat.aoc.client_pci.clients.over.OVERClient;
import cat.aoc.client_pci.clients.over.OVEROperacio;
import cat.aoc.client_pci.clients.over.OVERPeticionBuilder;
import cat.aoc.client_pci.clients.padro.PADROOperacio;
import cat.aoc.client_pci.clients.padro.PADROPeticionBuilder;
import cat.aoc.client_pci.clients.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.rca.RCAClient;
import cat.aoc.client_pci.clients.rca.RCAOperacio;
import cat.aoc.client_pci.clients.rca.RCAPeticionBuilder;
import cat.aoc.client_pci.clients.soc.SOCClient;
import cat.aoc.client_pci.clients.soc.SOCOperacio;
import cat.aoc.client_pci.clients.soc.SOCPeticionBuilder;
import cat.aoc.client_pci.clients.tfm.TFMClient;
import cat.aoc.client_pci.clients.tfm.TFMOperacio;
import cat.aoc.client_pci.clients.tfm.TFMPeticionBuilder;
import cat.aoc.client_pci.clients.tfn.TFNClient;
import cat.aoc.client_pci.clients.tfn.TFNOperacio;
import cat.aoc.client_pci.clients.tfn.TFNPeticionBuilder;
import cat.aoc.client_pci.model.exceptions.ClientException;
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
    OVER(Cluster.APP, "OVER"),
    TFN (Cluster.IOP, "TFN"),
    TFM (Cluster.IOP, "TFM"),
    DGP (Cluster.IOP, "DGP_IDENTITAT"),
    DGT (Cluster.IOP, "DGT"),
    SOC (Cluster.IOP, "SOC"),
    RCA (Cluster.IOP, "RCA"),
    PADRO(Cluster.IOP, "PADRO");

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private final Cluster cluster;
    private final String codi;

    Servei(Cluster cluster, String codi) {
        this.cluster = cluster;
        this.codi = codi;
    }

    public ClientAOC getClient(Entorn entorn) {
        return switch (this) {
            case ENOTUM -> new ENOTUMClient(KEYSTORE_PATH, entorn);
            case ETAULER -> new ETAULERClient(KEYSTORE_PATH, entorn);
            case OVER -> new OVERClient(KEYSTORE_PATH, entorn);
            case TFN -> new TFNClient(KEYSTORE_PATH, entorn);
            case TFM -> new TFMClient(KEYSTORE_PATH, entorn);
            case DGP -> new DGPClient(KEYSTORE_PATH, entorn);
            case DGT -> new DGTClient(KEYSTORE_PATH, entorn);
            case SOC -> new SOCClient(KEYSTORE_PATH, entorn);
            case RCA -> new RCAClient(KEYSTORE_PATH, entorn);
            case PADRO -> new PADROProxyClient(KEYSTORE_PATH, entorn);
        };
    }

    public Peticion getPeticion(Operacio operacio, Finalitat finalidad) throws ClientException {
        return switch (this) {
            case ENOTUM -> new ENOTUMPeticionBuilder(PROPERTIES_PATH).build((ENOTUMOperacio) operacio, finalidad);
            case ETAULER -> new ETAULERPeticionBuilder(PROPERTIES_PATH).build((ETAULEROperacio) operacio, finalidad);
            case OVER -> new OVERPeticionBuilder(PROPERTIES_PATH).build((OVEROperacio) operacio, finalidad);
            case TFN -> new TFNPeticionBuilder(PROPERTIES_PATH).build((TFNOperacio) operacio, finalidad);
            case TFM -> new TFMPeticionBuilder(PROPERTIES_PATH).build((TFMOperacio) operacio, finalidad);
            case DGP -> new DGPPeticionBuilder(PROPERTIES_PATH).build((DGPOperacio) operacio, finalidad);
            case DGT -> new DGTPeticionBuilder(PROPERTIES_PATH).build((DGTOperacio) operacio, finalidad);
            case SOC -> new SOCPeticionBuilder(PROPERTIES_PATH).build((SOCOperacio) operacio, finalidad);
            case RCA -> new RCAPeticionBuilder(PROPERTIES_PATH).build((RCAOperacio) operacio, finalidad);
            case PADRO -> new PADROPeticionBuilder(PROPERTIES_PATH).build((PADROOperacio) operacio, finalidad);
        };
    }

}
