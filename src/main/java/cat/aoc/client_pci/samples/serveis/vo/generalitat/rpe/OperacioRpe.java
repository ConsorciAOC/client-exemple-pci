package cat.aoc.client_pci.samples.serveis.vo.generalitat.rpe;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRpe implements Operacio {
    RPE_CONSULTA,
    RPE_VERIFICACIO;

    @Override
    public String getCodiProducte() {
        return "RPE";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
