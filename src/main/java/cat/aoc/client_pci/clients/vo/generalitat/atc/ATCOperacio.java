package cat.aoc.client_pci.clients.vo.generalitat.atc;

import cat.aoc.client_pci.model.Operacio;

public enum ATCOperacio implements Operacio {
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
