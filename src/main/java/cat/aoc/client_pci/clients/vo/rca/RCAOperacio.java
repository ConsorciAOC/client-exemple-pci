package cat.aoc.client_pci.clients.vo.rca;

import cat.aoc.client_pci.model.Operacio;

public enum RCAOperacio implements Operacio {
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
