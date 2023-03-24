package cat.aoc.client_pci.clients.vo.estat.sepe;

import cat.aoc.client_pci.model.Operacio;

public enum SEPEOperacio implements Operacio {
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
