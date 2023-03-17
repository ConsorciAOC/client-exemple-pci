package cat.aoc.client_pci.clients.vo.generalitat.soc;

import cat.aoc.client_pci.model.Operacio;

public enum SOCOperacio implements Operacio {
    SOC_CERT_INSCRIPCIO,
    SOC_CERT_ULTIMPERIODE,
    SOC_CERT_DADESPERSONALS,
    SOC_CERT_DONO;

    @Override
    public String getCodiProducte() {
        return "SOC";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
