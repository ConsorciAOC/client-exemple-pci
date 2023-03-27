package cat.aoc.client_pci.samples.serveis.vo.generalitat.rca;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRca implements Operacio {
    RCA_CONSULTA,
    RCA_VERIFICACIO;

    @Override
    public String getCodiProducte() {
        return "RCA";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
