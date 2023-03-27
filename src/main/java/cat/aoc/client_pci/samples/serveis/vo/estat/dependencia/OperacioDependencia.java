package cat.aoc.client_pci.samples.serveis.vo.estat.dependencia;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioDependencia implements Operacio {
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
