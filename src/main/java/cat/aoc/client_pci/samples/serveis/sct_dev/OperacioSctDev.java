package cat.aoc.client_pci.samples.serveis.sct_dev;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioSctDev implements Operacio {
    CONSULTA_CREDENCIALS,
    CONSULTA_CREDENCIALS_LOT;

    @Override
    public String getCodiProducte() {
        return "ENOTUM_CREDENCIALS";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
