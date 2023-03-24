package cat.aoc.client_pci.clients.vo.estat.estrangeria;

import cat.aoc.client_pci.model.Operacio;

public enum ESTRANGERIAOperacio implements Operacio {
    RESIDENCIA_LEGAL;

    @Override
    public String getCodiProducte() {
        return "ESTRANGERIA";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
