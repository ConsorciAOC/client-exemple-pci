package cat.aoc.client_pci.clients.vo.tfn;

import cat.aoc.client_pci.model.Operacio;

public enum TFNOperacio implements Operacio {
    TFN_VIGENCIA,
    TFN_DADESCOMPLETES,
    TFN_DADESCOMPLETES_DIS;

    @Override
    public String getCodiProducte() {
        return "TFN";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
