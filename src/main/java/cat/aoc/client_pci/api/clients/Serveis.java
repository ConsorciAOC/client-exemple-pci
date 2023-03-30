package cat.aoc.client_pci.api.clients;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.clients.proxy.ProxyClientCadastre;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.clients.proxy.ProxyClientPadro;
import cat.aoc.client_pci.utils.PropertiesReader;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

@Getter
public enum Serveis {
    BOE(Cluster.APP, "generated.boe"),
    EACAT(Cluster.APP, "generated.eacat"),
    ENOTUM(Cluster.NT, "generated.enotum"),
    ETAULER(Cluster.APP, "generated.etauler"),
    OVER(Cluster.APP, "generated.over"),
    REPRESENTA(Cluster.APP, "generated.representa"),
    SCT_DEV(Cluster.APP, "generated.sct_dev"),

    // VO - ESTAT
    CADASTRE(Cluster.IOP, "generated.cadastre.certificacio", "generated.cadastre.dades", "generated.cadastre.grafica", "generated.cadastre.csv"),
    DEPENDENCIA(Cluster.IOP, "generated.dependencia"),
    DGP(Cluster.IOP, "generated.dgp"),
    DGT(Cluster.IOP, "generated.dgt"),
    ESTRANGERIA(Cluster.IOP, "generated.estrangeria"),
    IGAE(Cluster.IOP, "generated.igae"),
    INSS(Cluster.IOP, "generated.inss", "generated.inss_historic"),
    MEPSYD(Cluster.IOP, "generated.mepsyd"),
    NOTARIS(Cluster.IOP, "generated.notaris"),
    REGISTRE_CIVIL(Cluster.IOP, "generated.registre_civil"),
    SEPE(Cluster.IOP, "generated.sepe"),

    // VO - GENERALITAT
    TFN(Cluster.IOP, "generated.tfn"),
    TFM(Cluster.IOP, "generated.tfm"),
    SOC(Cluster.IOP, "generated.soc"),
    RCA(Cluster.IOP, "generated.rca"),
    GRAU_DISCAPACITAT(Cluster.IOP, "generated.grau_discapacitat"),
    ATC(Cluster.IOP, "generated.atc"),
    REGISTRE_ENTITATS(Cluster.IOP, "generated.registre_entitats"),
    RGC(Cluster.IOP, "generated.rgc"),
    RPE(Cluster.IOP, "generated.rpe"),
    PADRO_HISTORIC(Cluster.IOP, "generated.padro_historic"),

    // VO - LOCAL
    PADRO(Cluster.IOP, "generated.padro", "generated.padro.empadronamiento");

    private static final String PROPERTIES_PATH = "src\\main\\resources\\client.properties";
    private static final String KEYSTORE_PATH = "src\\main\\resources\\keystore.properties";

    private final Cluster cluster;
    private final String[] packages;

    Serveis(Cluster cluster, String... packages) {
        this.cluster = cluster;
        this.packages = packages;
    }

    public ClientPCI getClient(Entorn entorn, Frontal frontal) throws IOException {
        Properties properties = PropertiesReader.load(KEYSTORE_PATH);
        return switch (this) {
            case PADRO -> new ProxyClientPadro(entorn, frontal, properties);
            case CADASTRE -> new ProxyClientCadastre(entorn, frontal, properties);
            default -> new ClientPCI(entorn, this.cluster, frontal, this.packages, properties);
        };
    }

}
