package cat.aoc.client_pci.clients.discapacitat;

import cat.aoc.client_pci.model.Operacio;

public enum DISCAPACITATOperacio implements Operacio {

    GRAU_DISCAPACITAT_SIMPLE,
    GRAU_DISCAPACITAT_TOTAL;

    @Override
    public String getCodiProducte() {
        return "GRAU_DISCAPACITAT";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
