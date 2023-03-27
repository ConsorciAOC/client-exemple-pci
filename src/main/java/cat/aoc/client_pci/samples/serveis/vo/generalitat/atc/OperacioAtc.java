package cat.aoc.client_pci.samples.serveis.vo.generalitat.atc;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioAtc implements Operacio {
    ATC_INF_DEUTES_TMP;

    @Override
    public String getCodiProducte() {
        return "ATC";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
