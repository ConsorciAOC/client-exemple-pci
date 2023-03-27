package cat.aoc.client_pci.samples.serveis.vo.estat.dgp;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioDgp implements Operacio {
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
