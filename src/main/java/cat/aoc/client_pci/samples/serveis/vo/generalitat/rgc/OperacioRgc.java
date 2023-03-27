package cat.aoc.client_pci.samples.serveis.vo.generalitat.rgc;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRgc implements Operacio {
    RGC_CONSULTA,
    RGC_CONSULTA_HISTORIC;

    @Override
    public String getCodiProducte() {
        return "RGC";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
