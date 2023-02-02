package cat.aoc.client_pci.model;

public enum Entorn {
    DEV,
    PRE,
    PRO;

    public String getEndpoint(Cluster cluster) {
        String clusterName = cluster.name().toLowerCase();
        return "https://" + getHost() + "." + clusterName + ".aoc.cat:443";
    }

    private String getHost() {
        return switch (this) {
            case DEV -> "serveis3-dev";
            case PRE -> "serveis3-pre";
            case PRO -> "serveis3";
        };
    }

}
