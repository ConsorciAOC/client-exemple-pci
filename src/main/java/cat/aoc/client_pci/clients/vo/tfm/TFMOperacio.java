package cat.aoc.client_pci.clients.vo.tfm;

import cat.aoc.client_pci.model.Operacio;

public enum TFMOperacio implements Operacio {
    TFM_VIGENCIA,
    TFM_DADESCOMPLETES,
    TFM_DADESCOMPLETES_DIS,
    TFM_DADESCOMPLETES_MASSIU,
    TFM_DADESCOMPLETES_DIS_MASSIU;

    @Override
    public String getCodiProducte() {
        return "TFM";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
