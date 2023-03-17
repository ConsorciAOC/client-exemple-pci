package cat.aoc.client_pci;

import cat.aoc.client_pci.clients.vo.atc.ATCClient;
import cat.aoc.client_pci.clients.vo.atc.ATCOperacio;
import cat.aoc.client_pci.clients.vo.atc.ATCPeticionBuilder;
import cat.aoc.client_pci.clients.vo.dgp.DGPClient;
import cat.aoc.client_pci.clients.vo.dgp.DGPOperacio;
import cat.aoc.client_pci.clients.vo.dgp.DGPPeticionBuilder;
import cat.aoc.client_pci.clients.vo.dgt.DGTClient;
import cat.aoc.client_pci.clients.vo.dgt.DGTOperacio;
import cat.aoc.client_pci.clients.vo.dgt.DGTPeticionBuilder;
import cat.aoc.client_pci.clients.vo.grau_discapacitat.DISCAPACITATClient;
import cat.aoc.client_pci.clients.vo.grau_discapacitat.DISCAPACITATOperacio;
import cat.aoc.client_pci.clients.vo.grau_discapacitat.DISCAPACITATPeticionBuilder;
import cat.aoc.client_pci.clients.enotum.ENOTUMClient;
import cat.aoc.client_pci.clients.enotum.ENOTUMOperacio;
import cat.aoc.client_pci.clients.enotum.ENOTUMPeticionBuilder;
import cat.aoc.client_pci.clients.etauler.ETAULERClient;
import cat.aoc.client_pci.clients.etauler.ETAULEROperacio;
import cat.aoc.client_pci.clients.etauler.ETAULERPeticionBuilder;
import cat.aoc.client_pci.clients.over.OVERClient;
import cat.aoc.client_pci.clients.over.OVEROperacio;
import cat.aoc.client_pci.clients.over.OVERPeticionBuilder;
import cat.aoc.client_pci.clients.vo.padro.PADROOperacio;
import cat.aoc.client_pci.clients.vo.padro.PADROPeticionBuilder;
import cat.aoc.client_pci.clients.vo.padro.PADROProxyClient;
import cat.aoc.client_pci.clients.vo.padro_historic.PADRO_HISTORICClient;
import cat.aoc.client_pci.clients.vo.padro_historic.PADRO_HISTORICOperacio;
import cat.aoc.client_pci.clients.vo.padro_historic.PADRO_HISTORICPeticionBuilder;
import cat.aoc.client_pci.clients.vo.rca.RCAClient;
import cat.aoc.client_pci.clients.vo.rca.RCAOperacio;
import cat.aoc.client_pci.clients.vo.rca.RCAPeticionBuilder;
import cat.aoc.client_pci.clients.vo.registre_entitats.REClient;
import cat.aoc.client_pci.clients.vo.registre_entitats.REOperacio;
import cat.aoc.client_pci.clients.vo.registre_entitats.REPeticionBuilder;
import cat.aoc.client_pci.clients.vo.rgc.RGCClient;
import cat.aoc.client_pci.clients.vo.rgc.RGCOperacio;
import cat.aoc.client_pci.clients.vo.rgc.RGCPeticionBuilder;
import cat.aoc.client_pci.clients.vo.rpe.RPEClient;
import cat.aoc.client_pci.clients.vo.rpe.RPEOperacio;
import cat.aoc.client_pci.clients.vo.rpe.RPEPeticionBuilder;
import cat.aoc.client_pci.clients.vo.soc.SOCClient;
import cat.aoc.client_pci.clients.vo.soc.SOCOperacio;
import cat.aoc.client_pci.clients.vo.soc.SOCPeticionBuilder;
import cat.aoc.client_pci.clients.vo.tfm.TFMClient;
import cat.aoc.client_pci.clients.vo.tfm.TFMOperacio;
import cat.aoc.client_pci.clients.vo.tfm.TFMPeticionBuilder;
import cat.aoc.client_pci.clients.vo.tfn.TFNClient;
import cat.aoc.client_pci.clients.vo.tfn.TFNOperacio;
import cat.aoc.client_pci.clients.vo.tfn.TFNPeticionBuilder;
import cat.aoc.client_pci.model.exceptions.ClientException;
import cat.aoc.client_pci.model.Cluster;
import cat.aoc.client_pci.model.Entorn;
import cat.aoc.client_pci.model.Finalitat;
import cat.aoc.client_pci.model.Operacio;
import lombok.Getter;
import net.gencat.scsp.esquemes.peticion.Peticion;

@Getter
public enum Servei {
    ENOTUM(Cluster.NT),
    ETAULER(Cluster.APP),
    OVER(Cluster.APP),
    TFN (Cluster.IOP),
    TFM (Cluster.IOP),
    DGP (Cluster.IOP),
    DGT (Cluster.IOP),
    SOC (Cluster.IOP),
    RCA (Cluster.IOP),
    GRAU_DISCAPACITAT (Cluster.IOP),
    ATC (Cluster.IOP),
    REGISTRE_ENTITATS (Cluster.IOP),
    RGC (Cluster.IOP),
    RPE (Cluster.IOP),
    PADRO_HISTORIC (Cluster.IOP),
    PADRO(Cluster.IOP);

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private final Cluster cluster;

    Servei(Cluster cluster) {
        this.cluster = cluster;
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
            case GRAU_DISCAPACITAT -> new DISCAPACITATClient(KEYSTORE_PATH, entorn);
            case ATC -> new ATCClient(KEYSTORE_PATH, entorn);
            case REGISTRE_ENTITATS -> new REClient(KEYSTORE_PATH, entorn);
            case RGC -> new RGCClient(KEYSTORE_PATH, entorn);
            case RPE -> new RPEClient(KEYSTORE_PATH, entorn);
            case PADRO_HISTORIC -> new PADRO_HISTORICClient(KEYSTORE_PATH, entorn);
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
            case GRAU_DISCAPACITAT -> new DISCAPACITATPeticionBuilder(PROPERTIES_PATH).build((DISCAPACITATOperacio) operacio, finalidad);
            case ATC -> new ATCPeticionBuilder(PROPERTIES_PATH).build((ATCOperacio) operacio, finalidad);
            case REGISTRE_ENTITATS -> new REPeticionBuilder(PROPERTIES_PATH).build((REOperacio) operacio, finalidad);
            case RGC -> new RGCPeticionBuilder(PROPERTIES_PATH).build((RGCOperacio) operacio, finalidad);
            case RPE -> new RPEPeticionBuilder(PROPERTIES_PATH).build((RPEOperacio) operacio, finalidad);
            case PADRO_HISTORIC -> new PADRO_HISTORICPeticionBuilder(PROPERTIES_PATH).build((PADRO_HISTORICOperacio) operacio, finalidad);
            case PADRO -> new PADROPeticionBuilder(PROPERTIES_PATH).build((PADROOperacio) operacio, finalidad);
        };
    }

}
