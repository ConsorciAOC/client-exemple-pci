package cat.aoc.client_pci.samples.serveis.vo.estat.igae;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioIgae implements Operacio {
    IGAE_INHABILITACIONS,
    IGAE_MINIMIS,
    IGAE_CONCESSIONS;

    @Override
    public String getCodiProducte() {
        return "IGAE";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
