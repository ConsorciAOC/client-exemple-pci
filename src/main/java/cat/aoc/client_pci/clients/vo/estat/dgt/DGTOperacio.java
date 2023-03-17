package cat.aoc.client_pci.clients.vo.estat.dgt;

import cat.aoc.client_pci.model.Operacio;

public enum DGTOperacio implements Operacio {
    DGT_DADES_VEHICLE,
    DGT_DADES_VEHICLE_SANCIONS,
    DGT_PERMISOS_CONDUCTOR,
    DGT_TITULAR_VIA,
    DGT_SANCIONS_CONDUCTOR,
    DGT_VEHICLES_CONDUCTOR,
    DGT_DISTINTIU_MEDIAMBIENTAL;

    @Override
    public String getCodiProducte() {
        return "DGT";
    }

    @Override
    public String getCodiModalitat() {
        return this.name();
    }

}
