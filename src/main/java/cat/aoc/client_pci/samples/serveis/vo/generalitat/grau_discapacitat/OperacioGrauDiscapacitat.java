package cat.aoc.client_pci.samples.serveis.vo.generalitat.grau_discapacitat;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioGrauDiscapacitat implements Operacio {

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
