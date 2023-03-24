package cat.aoc.client_pci.clients.vo.estat.dependencia;

import cat.aoc.client_pci.model.Operacio;

public enum DEPENDENCIAOperacio implements Operacio {
    GRAU_DEPENDENCIA;

    @Override
    public String getCodiProducte() {
        return "DEPENDENCIA";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
