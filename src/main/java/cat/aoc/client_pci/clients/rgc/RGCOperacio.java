package cat.aoc.client_pci.clients.rgc;

import cat.aoc.client_pci.model.Operacio;

public enum RGCOperacio implements Operacio {
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
