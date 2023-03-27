package cat.aoc.client_pci.samples.serveis.vo.estat.sepe;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioSepe implements Operacio {
    VERIF_DADES_ATUR,
    VERIF_IMPORTS_ACTUALS,
    VERIF_IMPORTS_PERIODE;

    @Override
    public String getCodiProducte() {
        return "INEM";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
