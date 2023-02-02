package cat.aoc.client_pci.clients.tfn;

import cat.aoc.client_pci.model.Operacio;

public enum TFNOperacio implements Operacio {
    TFN_VIGENCIA,
    TFN_DADESCOMPLETES,
    TFN_DADESCOMPLETES_DIS;

    @Override
    public String getCodiModalitat() {
        return this.name();
    }
}
