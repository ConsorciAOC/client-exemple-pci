package cat.aoc.client_pci.samples.serveis.eacat;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioEacat implements Operacio {
    EACAT_USUARI,
    EACAT_SERVEI,
    EACAT_ENS,
    EACAT_TIPUS_ENS;

    @Override
    public String getCodiProducte() {
        return "EACAT";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
