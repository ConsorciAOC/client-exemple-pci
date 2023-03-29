package cat.aoc.client_pci.samples.serveis.vo.estat.mepsyd;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioMepsyd implements Operacio {
    TITOLS_UNIVERSITARIS,
    TITOLS_NO_UNIVERSITARIS,
    TITOLS_UNIVERSITARIS_LLISTAT,
    TITOLS_NO_UNIVERSITARIS_LLISTAT;

    @Override
    public String getCodiProducte() {
        return "MEPSYD";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
