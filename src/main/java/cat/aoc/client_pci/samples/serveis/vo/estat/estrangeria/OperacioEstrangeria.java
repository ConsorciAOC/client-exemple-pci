package cat.aoc.client_pci.samples.serveis.vo.estat.estrangeria;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioEstrangeria implements Operacio {
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
