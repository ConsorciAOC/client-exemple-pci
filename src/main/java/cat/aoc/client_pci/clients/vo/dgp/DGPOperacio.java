package cat.aoc.client_pci.clients.vo.dgp;

import cat.aoc.client_pci.model.Operacio;

public enum DGPOperacio implements Operacio {
    IDENTITAT_DADES,
    IDENTITAT_VERIFICACIO;

    @Override
    public String getCodiProducte() {
        return "DGP_IDENTITAT";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
