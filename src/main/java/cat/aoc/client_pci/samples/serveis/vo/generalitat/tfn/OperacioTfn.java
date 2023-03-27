package cat.aoc.client_pci.samples.serveis.vo.generalitat.tfn;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioTfn implements Operacio {
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
