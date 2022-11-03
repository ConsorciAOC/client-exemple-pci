package cat.aoc.client_pci.model;

import cat.aoc.client_pci.exceptions.NotDefinedException;

public enum Entorn {
    DEV,
    PRE,
    PRO;

    public String getEndpoint(Cluster cluster) throws NotDefinedException {
        switch (this) {
            case DEV:
                return buildEndpoint("https://serveis3-dev.", cluster);
            case PRE:
                return buildEndpoint("https://serveis3-pre.", cluster);
            case PRO:
                return buildEndpoint("https://serveis3.", cluster);
            default:
                throw new NotDefinedException("Entorn no definit: " + this.name());
        }
    }

    private String buildEndpoint(String start, Cluster cluster) {
        return start + cluster.name().toLowerCase() + ".aoc.cat:443";
    }

}
