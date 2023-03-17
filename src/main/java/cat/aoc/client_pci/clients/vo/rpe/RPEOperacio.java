package cat.aoc.client_pci.clients.vo.rpe;

import cat.aoc.client_pci.model.Operacio;

public enum RPEOperacio implements Operacio {
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
